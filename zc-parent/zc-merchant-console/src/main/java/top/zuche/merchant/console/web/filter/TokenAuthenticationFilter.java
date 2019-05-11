package top.zuche.merchant.console.web.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;
import top.zuche.merchant.console.utils.TokenUtil;
import top.zuche.merchant.console.web.security.TokenAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token认证过滤器，在UsernamePasswordAuthenticationFilter之后。
 * 用于验证api请求中的token
 *
 * @author lzx
 * @date 2019/5/9 下午11:14
 */
@Getter
@Setter
public class TokenAuthenticationFilter extends GenericFilterBean {

    private RequestMatcher loginRequestMatcher;

    private boolean continueChainBeforeSuccessfulAuthentication = false;

    protected ApplicationEventPublisher eventPublisher;

    private AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

    private AuthenticationManager authenticationManager;

    private String usernameParameter = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
    private String passwordParameter = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // 如果不需要认证，直接忽略
        if (!requiresAuthentication(request, response)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            // 如果是登录请求，那么处理登录中的用户名，密码等；
            Authentication authResult = attemptAuthentication(request, response);

            authResult = toTokenAuthenticationToken(authResult);

            // 成功认证，放入权限上下文
            successfulAuthentication(request, response, chain, authResult);

        } catch (InternalAuthenticationServiceException failed) {
            logger.error(
                    "An internal error occurred while trying to authenticate the user.",
                    failed);
            unsuccessfulAuthentication(request, response, failed);
        } catch (AuthenticationException failed) {
            // Authentication failed
            unsuccessfulAuthentication(request, response, failed);
        }
    }

    /**
     * 判断是否需要认证
     *
     * @param request
     * @param response
     * @return
     */
    private boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return loginRequestMatcher.matches(request);
    }

    /**
     * 尝试通过token来认证
     *
     * @param request
     * @param response
     * @return
     */
    private Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        // RedisSecurityContextRepository已经加载到一个空Context
        String username = request.getParameter(usernameParameter);
        String password = request.getParameter(passwordParameter);
        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 转换认证对象
     * @param authentication
     * @return
     */
    private TokenAuthenticationToken toTokenAuthenticationToken(Authentication authentication) {
        UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken) authentication;
        String token = TokenUtil.generateNew();
        TokenAuthenticationToken tat = new TokenAuthenticationToken(upat.getPrincipal(), upat.getCredentials(), token, upat.getAuthorities());
        tat.setDetails(upat.getDetails());
        return tat;
    }

    /**
     * 认证失败
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    private void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        SecurityContextHolder.clearContext();

        if (logger.isDebugEnabled()) {
            logger.debug("Authentication request failed: " + failed.toString(), failed);
            logger.debug("Updated SecurityContextHolder to contain null Authentication");
            logger.debug("Delegating to authentication failure handler " + failureHandler);
        }

        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    /**
     * 成功认证
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    private void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        if (logger.isDebugEnabled()) {
            logger.debug("Authentication success. Updating SecurityContextHolder to contain: "
                    + authResult);
        }

        SecurityContextHolder.getContext().setAuthentication(authResult);

        // Fire event
        if (this.eventPublisher != null) {
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(
                    authResult, this.getClass()));
        }

        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
