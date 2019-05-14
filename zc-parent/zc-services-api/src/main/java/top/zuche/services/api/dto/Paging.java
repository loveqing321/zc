package top.zuche.services.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @author lzx
 * @date 2019/5/13 上午2:26
 */
@Data
public class Paging<T> implements Serializable {

    private long total;

    private List<T> result;

    public Paging() {}

    private Paging(long total, List<T> result) {
        this.total = total;
        this.result = result;
    }

    public static <T> Paging<T> of(long total, List<T> result) {
        return new Paging<>(total, result);
    }
}
