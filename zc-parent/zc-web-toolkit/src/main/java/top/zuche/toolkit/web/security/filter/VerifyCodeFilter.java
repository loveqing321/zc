package top.zuche.toolkit.web.security.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;
import top.zuche.toolkit.web.security.exception.InvalidVerifyCodeException;
import top.zuche.toolkit.web.security.exception.VerifyCodeExpiredException;
import top.zuche.toolkit.web.security.verifycode.VerifyCode;
import top.zuche.toolkit.web.security.verifycode.repository.VerifyCodeRepository;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 验证码过滤器
 * @author lzx
 * @date 2019/5/9 下午3:59
 */
@Getter
@Setter
public class VerifyCodeFilter extends GenericFilterBean {

    // 验证码参数名
    private static final String DEFAULT_VERIFY_CODE_PARAM = "verifyCode";

    @Resource
    private VerifyCodeRepository verifyCodeRepository;

    private RequestMatcher requestMatcher = new AntPathRequestMatcher("/login", "POST");

    private String verifyCodeParameter = DEFAULT_VERIFY_CODE_PARAM;

    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // 只过滤登录请求
        if (!requestMatcher.matches(req)) {
            chain.doFilter(request, response);
            return;
        }

        // 从请求中获取验证码参数
        String verifyCode = req.getParameter(verifyCodeParameter);
        if (verifyCode == null) {
            failureHandler.onAuthenticationFailure(req, res, new InvalidVerifyCodeException());
            return;
        }

        // 从存储中获取验证码
        VerifyCode codeStored = verifyCodeRepository.loadCode(req);

        if (codeStored == null) {
            failureHandler.onAuthenticationFailure(req, res, new InvalidVerifyCodeException());
            return;
        }
        // 验证码过期
        if (codeStored.isExpire(LocalDateTime.now())) {
            // 验证码过期，删除之
            verifyCodeRepository.saveCode(null, req, res);
            failureHandler.onAuthenticationFailure(req, res, new VerifyCodeExpiredException());
            return;
        }
        if (!verifyCode.equals(codeStored.getRandom())) {
            failureHandler.onAuthenticationFailure(req, res, new InvalidVerifyCodeException());
            return;
        }
        // 验证成功，从存储中移除中移除验证码
        verifyCodeRepository.saveCode(null, req, res);
        chain.doFilter(request, response);
    }

    /**
     * 设置登录地址
     * @param loginUrl
     */
    public void setLoginUrl(String loginUrl) {
        this.requestMatcher = new AntPathRequestMatcher(loginUrl, "POST");
    }

    /**
     * 设置验证码参数名
     * @param verifyCodeParameter
     */
    public void verifyCodeParameter(String verifyCodeParameter) {
        this.verifyCodeParameter = verifyCodeParameter;
    }
}
