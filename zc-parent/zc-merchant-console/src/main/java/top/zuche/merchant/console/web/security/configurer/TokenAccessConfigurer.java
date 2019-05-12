package top.zuche.merchant.console.web.security.configurer;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import top.zuche.merchant.console.web.filter.TokenAuthenticationFilter;
import top.zuche.merchant.console.web.security.TokenLogoutHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Token访问配置，用于构建TokenAuthenticationFilter
 *
 * @author lzx
 * @date 2019/5/9 下午11:43
 */
public class TokenAccessConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<TokenAccessConfigurer<H>, H> {

    private String loginUrl;

    private String[] ignoreUrls;

    private String usernameParameter;

    private String passwordParameter;

    private AuthenticationSuccessHandler successHandler;

    private AuthenticationFailureHandler failureHandler;

    public TokenAccessConfigurer<H> loginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
        return this;
    }

    public TokenAccessConfigurer<H> ignoreUrls(String... ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
        return this;
    }

    public TokenAccessConfigurer<H> usernameParameter(String usernameParameter) {
        this.usernameParameter = usernameParameter;
        return this;
    }

    public TokenAccessConfigurer<H> passwordParameter(String passwordParameter) {
        this.passwordParameter = passwordParameter;
        return this;
    }

    public TokenAccessConfigurer<H> successHandler(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
        return this;
    }

    public TokenAccessConfigurer<H> failureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void init(H http) throws Exception {
        super.init(http);
        // 添加退出处理
        SecurityContextRepository securityContextRepository = http.getSharedObject(SecurityContextRepository.class);
        LogoutConfigurer<H> logoutConfigurer = http.getConfigurer(LogoutConfigurer.class);
        if (logoutConfigurer != null) {
            logoutConfigurer.addLogoutHandler(new TokenLogoutHandler(securityContextRepository));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void configure(H http) throws Exception {
        TokenAuthenticationFilter authFilter = new TokenAuthenticationFilter();
        if (loginUrl == null) {
            throw new Exception("Must special the login url.");
        }
        authFilter.setLoginRequestMatcher(new AntPathRequestMatcher(loginUrl, "POST"));
        if (ignoreUrls != null && ignoreUrls.length > 0) {
            List<RequestMatcher> ignoreRequestMatchers = new ArrayList<>(ignoreUrls.length);
            Arrays.stream(ignoreUrls).forEach(ignoreUrl -> {
                ignoreRequestMatchers.add(new AntPathRequestMatcher(ignoreUrl));
            });
            authFilter.setIgnoreRequestMatchers(ignoreRequestMatchers);
        }
        if (usernameParameter != null) {
            authFilter.setUsernameParameter(usernameParameter);
        }
        if (passwordParameter != null) {
            authFilter.setPasswordParameter(passwordParameter);
        }
        if (successHandler != null) {
            authFilter.setSuccessHandler(successHandler);
        }
        if (failureHandler != null) {
            authFilter.setFailureHandler(failureHandler);
        }

        authFilter = postProcess(authFilter);

        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        authFilter.setAuthenticationManager(authenticationManager);
        // 在UsernamePasswordAuthenticationFilter 之前添加
        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        // 设置到共享对象中
        http.setSharedObject(TokenAuthenticationFilter.class, authFilter);
    }
}
