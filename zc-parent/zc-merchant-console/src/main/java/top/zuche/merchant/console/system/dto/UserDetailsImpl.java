package top.zuche.merchant.console.system.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户信息
 *
 * @author lzx
 * @date 2019/5/11 上午9:22
 */
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    // 登录名
    private String username;
    // 密码
    private String password;
    // 权限信息
    private Collection<? extends GrantedAuthority> authorities;
    // 账号是否过期
    private boolean accountNonExpired = true;
    // 账号是否锁定
    private boolean accountNonLocked = true;
    // 密码是否过期
    private boolean credentialsNonExpired = true;
    // 是否启用状态
    private boolean enabled = true;

    // 额外属性
    // 用户ID
    private Integer userId;
    // 商户ID - 关联到商户表
    private Integer merchantId;
    // 中文名
    private String cname;
    // 电话号码
    private String telephone;
    // 部门
    private String department;
    // 职位
    private String post;

}
