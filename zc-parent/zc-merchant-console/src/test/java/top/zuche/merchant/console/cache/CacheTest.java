package top.zuche.merchant.console.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.zuche.merchant.console.order.service.IOrderService;

import javax.annotation.Resource;

/**
 * @author lzx
 * @date 2019/5/8 上午1:37
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CacheTest {

    @Resource(name = "orderServiceImpl")
    private IOrderService orderService;

    @Test
    public void testSave() {
        String id = "a";
        String content = "bcd";
        orderService.save(id, content);

        String old = orderService.get(id);
        System.out.println(old);

        old = orderService.get(id);
        System.out.println(old);

        orderService.clear();

        old = orderService.get(id);
        System.out.println(old);
    }
}
