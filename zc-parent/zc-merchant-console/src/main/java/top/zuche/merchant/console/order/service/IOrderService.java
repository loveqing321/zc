package top.zuche.merchant.console.order.service;

/**
 * @author lzx
 * @date 2019/5/8 上午10:11
 */
public interface IOrderService {

    String save(String id, String content);

    String get(String id);

    void delete(String id);

    String update(String id, String content);

    void clear();
}
