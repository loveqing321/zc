package top.zuche.toolkit.web.security.configurer;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import top.zuche.toolkit.web.security.filter.VerifyCodeFilter;

/**
 * 验证码的配置对象，用于构建VerifyCodeFilter
 *
 * @author lzx
 * @date 2019/5/9 下午11:43
 */
public class VerifyCodeConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<VerifyCodeConfigurer<H>, H> {

    // 只有登录地址，会校验验证码
    private String loginUrl;
    // 验证码参数名
    private String verifyCodeParameter;
    // 校验失败处理器
    private AuthenticationFailureHandler failureHandler;

    public VerifyCodeConfigurer<H> loginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
        return this;
    }

    public VerifyCodeConfigurer<H> verifyCodeParameter(String verifyCodeParameter) {
        this.verifyCodeParameter = verifyCodeParameter;
        return this;
    }

    public VerifyCodeConfigurer<H> failureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
        return this;
    }

    @Override
    public void configure(H http) throws Exception {
        VerifyCodeFilter codeFilter = new VerifyCodeFilter();
        if (loginUrl == null) {
            throw new Exception("Must special the login url.");
        }
        codeFilter.setLoginUrl(loginUrl);
        if (verifyCodeParameter != null) {
            codeFilter.verifyCodeParameter(verifyCodeParameter);
        }
        if (failureHandler != null) {
            codeFilter.setFailureHandler(failureHandler);
        }
        codeFilter = postProcess(codeFilter);
        // 在 AbstractPreAuthenticatedProcessingFilter 之前添加
        http.addFilterBefore(codeFilter, AbstractPreAuthenticatedProcessingFilter.class);
        // 分享
        http.setSharedObject(VerifyCodeFilter.class, codeFilter);
    }
}
