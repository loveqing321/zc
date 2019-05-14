package top.zuche.services.api.query;

import lombok.Data;

/**
 * @author lzx
 * @date 2019/5/14 上午12:04
 */
@Data
public class UserQuery extends PageQuery {

    // 用户名 / 账号 / 电话
    private String searchText;

    // 是否废弃
    private Integer isDeleted;

}
