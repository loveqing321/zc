package top.zuche.merchant.console.web.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Token不存在异常
 * @author lzx
 * @date 2019/5/10 上午12:22
 */
public class TokenNotFoundException extends AuthenticationException {

    public TokenNotFoundException(){
        super(null);
    }

    public TokenNotFoundException(String msg) {
        super(msg);
    }

    public TokenNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

}
