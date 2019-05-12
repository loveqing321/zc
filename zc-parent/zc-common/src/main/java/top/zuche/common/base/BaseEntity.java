package top.zuche.common.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lzx
 * @date 2019/5/12 上午8:23
 */
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

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

}
