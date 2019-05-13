package top.zuche.common.web.query;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分页查询对象
 *
 * @author lzx
 * @date 2019/5/13 上午9:05
 */
@Setter
@Getter
public class PageQuery implements Serializable {

    // 当前分页
    private int pageNo;

    // 分页大小
    private int pageSize;
}
