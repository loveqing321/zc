package top.zuche.services.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 许可实体
 *
 * @author lzx
 * @date 2019/5/12 上午8:22
 */
@Getter
@Setter
public class PermissionDTO implements Serializable {

    // 许可ID
    private Integer id;

    // 许可名称
    private String name;

    // 许可字符串
    private String perm;

    // 许可分类
    private String category;

    // 是否删除 0 - 未删除  1 / null - 删除
    private Integer isDeleted;

}
