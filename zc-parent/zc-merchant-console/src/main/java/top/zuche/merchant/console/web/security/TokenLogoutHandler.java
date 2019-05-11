package top.zuche.merchant.console.web.security;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Token退出处理器，因为token是绑定到SecurityContext上的，因此只需要移除SecurityContext即可；
 * 框架自带的SecurityContextLogoutHandler，并没有对SecurityContextRepository进行移除，因此这里，重写之。
 *
 * @author lzx
 * @date 2019/5/11 上午12:17
 */
public class TokenLogoutHandler implements LogoutHandler {

    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    private SecurityContextRepository securityContextRepository;

    public TokenLogoutHandler(SecurityContextRepository securityContextRepository) {
        this.securityContextRepository = securityContextRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 过滤其他的情况
        if (authentication != null && !trustResolver.isAnonymous(authentication)) {
            // 返回认证后的TokenAuthenticationToken对象
            TokenAuthenticationToken tokenAuthentication = (TokenAuthenticationToken) authentication;
            String token = tokenAuthentication.getToken();
            // 目前仅处理Redis的情况
            if (securityContextRepository instanceof RedisSecurityContextRepository) {
                ((RedisSecurityContextRepository) securityContextRepository).saveSecurityContextToRedis(token, null);
            }
        }
    }
}
