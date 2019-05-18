package top.zuche.services.system.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典Entity
 *
 * @author lzx
 * @date 2019/5/16 上午5:13
 */
@Data
public class DictEntity implements Serializable {

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

}
