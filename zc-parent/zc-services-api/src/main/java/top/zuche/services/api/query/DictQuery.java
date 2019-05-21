package top.zuche.services.api.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lzx
 * @date 2019/5/16 上午5:09
 */
@Data
public class DictQuery extends PageQuery implements Serializable {

    // 字典类型
    private String dictType;

    // 字典编码
    private String dictCode;

    // 字典值
    private String dictValue;

    // 是否删除
    private Integer isDeleted;
}
