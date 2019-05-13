package top.zuche.toolkit.web.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Token过期异常
 * @author lzx
 * @date 2019/5/10 上午12:22
 */
public class TokenExpiredException extends AuthenticationException {

    public TokenExpiredException(){
        super(null);
    }

    public TokenExpiredException(String msg) {
        super(msg);
    }

    public TokenExpiredException(String msg, Throwable t) {
        super(msg, t);
    }

}
