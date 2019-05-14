package top.zuche.manager.console.system.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author lzx
 * @date 2019/5/13 下午11:30
 */
@Getter
@Setter
public class UserDetailsImpl implements UserDetails, Serializable {

    private transient BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private String cname = "Admin";

    // 登录名
    private String username = "admin";
    // 密码
    private String password = encoder.encode("123");
    // 权限信息
    private Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("admin");
    // 账号是否过期
    private boolean accountNonExpired = true;
    // 账号是否锁定
    private boolean accountNonLocked = true;
    // 密码是否过期
    private boolean credentialsNonExpired = true;
    // 是否启用状态
    private boolean enabled = true;

}
