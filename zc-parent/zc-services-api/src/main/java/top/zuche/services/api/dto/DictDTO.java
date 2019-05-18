package top.zuche.services.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典对象
 *
 * @author lzx
 * @date 2019/5/16 上午5:09
 */
@Data
public class DictDTO implements Serializable {

    // 字典ID
    private Integer id;

    // 字典类型
    private String dictType;

    // 字典编码
    private String dictCode;

    // 字典值
    private String dictValue;

    // 字典描述
    private String dictDesc;

    // 是否删除 0 - 未删除  1 / null - 删除
    private Integer isDeleted;
}
