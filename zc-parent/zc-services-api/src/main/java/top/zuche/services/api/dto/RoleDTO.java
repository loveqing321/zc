package top.zuche.services.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 角色DTO
 *
 * @author lzx
 * @date 2019/5/11 上午10:56
 */
@Getter
@Setter
public class RoleDTO implements Serializable {

    // 角色ID
    private Integer id;

    // 角色名称
    private String roleName;

    // 是否删除 0 - 未删除  1 / null - 删除
    private Integer isDeleted;
}
