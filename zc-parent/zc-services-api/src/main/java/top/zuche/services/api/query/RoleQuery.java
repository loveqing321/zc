package top.zuche.services.api.query;

import lombok.Data;

/**
 * @author lzx
 * @date 2019/5/14 上午12:04
 */
@Data
public class RoleQuery extends PageQuery {

    // 角色名
    private String roleName;

    // 是否废弃
    private Integer isDeleted;

}
