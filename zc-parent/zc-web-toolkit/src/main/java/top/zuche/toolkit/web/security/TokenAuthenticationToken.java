package top.zuche.toolkit.web.security;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 基于token的认证结果
 *
 * @author lzx
 * @date 2019/5/9 下午9:48
 */
@Getter
public class TokenAuthenticationToken extends UsernamePasswordAuthenticationToken {

    // token
    private final String token;

    public TokenAuthenticationToken(Object principal, Object credentials, String token) {
        super(principal, credentials);
        this.token = token;
    }

    public TokenAuthenticationToken(Object principal, Object credentials, String token, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.token = token;
    }
}
