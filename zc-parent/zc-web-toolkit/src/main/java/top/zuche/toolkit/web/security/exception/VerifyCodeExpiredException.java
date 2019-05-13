package top.zuche.toolkit.web.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码过期异常
 * @author lzx
 * @date 2019/5/10 上午12:22
 */
public class VerifyCodeExpiredException extends AuthenticationException {

    public VerifyCodeExpiredException(){
        super(null);
    }

    public VerifyCodeExpiredException(String msg) {
        super(msg);
    }

    public VerifyCodeExpiredException(String msg, Throwable t) {
        super(msg, t);
    }

}
