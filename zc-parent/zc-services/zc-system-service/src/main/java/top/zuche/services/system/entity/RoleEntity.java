package top.zuche.services.system.entity;

import lombok.Getter;
import lombok.Setter;
import top.zuche.common.base.BaseEntity;

import java.io.Serializable;

/**
 * @author lzx
 * @date 2019/5/12 上午8:22
 */
@Getter
@Setter
public class RoleEntity extends BaseEntity implements Serializable {

    // 角色ID
    private Integer id;

    // 角色名称
    private String roleName;

}
