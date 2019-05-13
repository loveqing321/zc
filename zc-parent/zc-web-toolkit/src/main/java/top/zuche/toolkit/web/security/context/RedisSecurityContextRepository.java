package top.zuche.toolkit.web.security.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SaveContextOnUpdateOrErrorResponseWrapper;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;
import top.zuche.toolkit.utils.TokenUtil;
import top.zuche.toolkit.web.security.SecurityProperties;
import top.zuche.toolkit.web.security.TokenAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * Redis权限上下文存储
 *
 * @author lzx
 * @date 2019/5/9 下午10:13
 */
@Slf4j
public class RedisSecurityContextRepository implements SecurityContextRepository {

    // 默认权限上下文key
    private static final String DEFAULT_SECURITY_CONTEXT_KEY = "security_context_%s";

    // 默认权限超时 30分钟 IDLE
    private static final int DEFAULT_SECURITY_CONTEXT_TIMEOUT = 30;

    // 权限上下文key
    private String securityContextKeyFormat = DEFAULT_SECURITY_CONTEXT_KEY;

    private int securityContextTimeout = DEFAULT_SECURITY_CONTEXT_TIMEOUT;

    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    private RedisTemplate redisTemplate;

    private SecurityProperties securityProperties;

    public RedisSecurityContextRepository(RedisTemplate redisTemplate, SecurityProperties securityProperties) {
        this.redisTemplate = redisTemplate;
        this.securityProperties = securityProperties;
        // 设置token超时
        Integer tokenTtl = securityProperties.getTokenTtl();
        if (tokenTtl != null && tokenTtl > 0) {
            this.securityContextTimeout = tokenTtl;
        }
        String scKey = securityProperties.getSecurityContextKeyFormat();
        if (StringUtils.hasText(scKey)) {
            this.securityContextKeyFormat = scKey;
        }
    }

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        HttpServletResponse response = requestResponseHolder.getResponse();

        String token = TokenUtil.getTokenFromRequest(request);
        SecurityContext context;
        if (!StringUtils.hasText(token)) {
            if (log.isDebugEnabled()) {
                log.debug("No SecurityContext was available from the redis. A new one will be created.");
            }
            context = generateNewContext();
        } else {
            context = readSecurityContextFromRedis(token);
            if (context == null) {
                if (log.isDebugEnabled()) {
                    log.debug("No SecurityContext is expired. A new one will be created.");
                }
                context = generateNewContext();
            }
        }

        SaveToRedisResponseWrapper wrappedResponse = new SaveToRedisResponseWrapper(response);
        requestResponseHolder.setResponse(wrappedResponse);

        return context;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        SaveToRedisResponseWrapper responseWrapper = WebUtils.getNativeResponse(response, SaveToRedisResponseWrapper.class);
        if (responseWrapper == null) {
            throw new IllegalStateException("Cannot invoke saveContext on response "
                            + response
                            + ". You must use the HttpRequestResponseHolder.response after invoking loadContext");
        }

        if (!responseWrapper.isContextSaved()) {
            responseWrapper.saveContext(context);
        }
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        String token = TokenUtil.getTokenFromRequest(request);
        if (!StringUtils.hasText(token)) {
            return false;
        }
        return readSecurityContextFromRedis(token) != null;
    }

    /**
     * 从Redis中读取权限上下文
     *
     * @param token
     * @return
     */
    private SecurityContext readSecurityContextFromRedis(String token) {
        return (SecurityContext) redisTemplate.opsForValue().get(securityContextKey(token));
    }

    /**
     * 保存权限上下文
     *
     * @param token
     * @param context
     */
    public void saveSecurityContextToRedis(String token, SecurityContext context) {
        if (context == null) {
            redisTemplate.delete(securityContextKey(token));
        } else {
            redisTemplate.opsForValue().set(securityContextKey(token), context, securityContextTimeout, TimeUnit.MINUTES);
        }
    }

    /**
     * 生成新的上下文
     *
     * @return
     */
    private SecurityContext generateNewContext() {
        return SecurityContextHolder.createEmptyContext();
    }

    /**
     * 权限上下文Key
     * @param token
     * @return
     */
    private String securityContextKey(String token) {
        return String.format(securityContextKeyFormat, token);
    }

    final class SaveToRedisResponseWrapper extends SaveContextOnUpdateOrErrorResponseWrapper {

        SaveToRedisResponseWrapper(HttpServletResponse response) {
            super(response, false);
        }

        @Override
        protected void saveContext(SecurityContext context) {
            final Authentication authentication = context.getAuthentication();

            // 权限上下文是空的
            if (authentication == null || trustResolver.isAnonymous(authentication)) {
                return;
            }

            // 返回认证后的TokenAuthenticationToken对象
            TokenAuthenticationToken tokenAuthentication = (TokenAuthenticationToken) authentication;
            String token = tokenAuthentication.getToken();

            // 保存权限上下文
            saveSecurityContextToRedis(token, context);
        }
    }
}
