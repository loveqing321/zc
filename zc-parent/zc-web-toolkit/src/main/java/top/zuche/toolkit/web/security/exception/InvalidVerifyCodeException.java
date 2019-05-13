package top.zuche.toolkit.web.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码无效
 * @author lzx
 * @date 2019/5/10 上午12:22
 */
public class InvalidVerifyCodeException extends AuthenticationException {

    public InvalidVerifyCodeException(){
        super(null);
    }

    public InvalidVerifyCodeException(String msg) {
        super(msg);
    }

    public InvalidVerifyCodeException(String msg, Throwable t) {
        super(msg, t);
    }

}
