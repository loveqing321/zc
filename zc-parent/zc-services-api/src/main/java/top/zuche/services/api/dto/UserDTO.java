package top.zuche.services.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户DTO
 *
 * @author lzx
 * @date 2019/5/9 下午8:20
 */
@Getter
@Setter
public class UserDTO implements Serializable {

    // 用户ID
    private Integer id;

    // 商户ID - 关联到商户表
    private Integer merchantId;

    // 登录用户名，常和电话号码一致
    private String username;

    // 密码
    private String password;

    // 中文名称
    private String cname;

    // 电话号码
    private String telephone;

    // 部门
    private String department;

    // 职位
    private String post;

    // 账号是否过期 0 - 不过期， 1 / null - 过期
    private Integer accountExpired;

    // 账号是否锁定 0 - 未锁定， 1 / null - 锁定
    private Integer accountLocked;

    // 密码是否过期 0 - 未过期， 1 / null - 过期
    private Integer credentialsExpired;

    // 创建人ID
    private Integer createUser;

    // 创建时间
    private Date createTime;

    // 修改人ID
    private Integer modifyUser;

    // 修改时间
    private Date modifyTime;

    // 是否删除 0 - 未删除  1 / null - 删除
    private Integer isDeleted;

    // 角色列表
    private List<RoleDTO> roles;
}
