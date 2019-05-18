package top.zuche.services.api.query;

import lombok.Data;

/**
 * @author lzx
 * @date 2019/5/14 上午12:04
 */
@Data
public class PermissionQuery extends PageQuery {

    // 名称
    private String nameOrPerm;

    // 类别
    private String category;

    // 是否启用
    private Integer isDeleted;
}
