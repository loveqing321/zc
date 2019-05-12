package top.zuche.common.web.resp;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @author lzx
 * @date 2019/5/13 上午2:26
 */
public class Paging<T> implements Serializable {

    private final long total;

    private final List<T> result;

    private Paging(long total, List<T> result) {
        this.total = total;
        this.result = result;
    }

    public static <T> Paging<T> of(long total, List<T> result) {
        return new Paging<>(total, result);
    }
}
