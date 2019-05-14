package top.zuche.services.api.query;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author lzx
 * @date 2019/5/14 上午12:05
 */
@Getter
@Setter
public abstract class PageQuery implements Serializable {

    // 当前分页
    private int pageNo;

    // 分页大小
    private int pageSize;
}
