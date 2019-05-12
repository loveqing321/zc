package top.zuche.merchant.console.system.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import top.zuche.services.api.dto.UserDTO;
import top.zuche.services.api.service.UserRpcService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzx
 * @date 2019/5/12 下午1:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserRpcServiceTest {

    @Reference(interfaceClass = UserRpcService.class)
    private UserRpcService userRpcService;

    @Test
    public void batchAddUser() {
        List<UserDTO> users = new ArrayList<>();
        UserDTO user1 = new UserDTO();
        user1.setMerchantId(1);
        user1.setUsername("13333201151");
        user1.setDepartment("开发");
        user1.setPost("好的");
        user1.setCname("刘振兴");
        user1.setPassword("$2a$10$LbAO/q6OlhB8KXkxlwLbf.OOuWAnukVxxi6Ge.KV1MXHt0ymhokHu");

        UserDTO user2 = new UserDTO();
        user2.setMerchantId(1);
        user2.setUsername("13333201150");
        user2.setDepartment("开发");
        user2.setPost("好的");
        user2.setCname("刘振兴");
        user2.setPassword("$2a$10$LbAO/q6OlhB8KXkxlwLbf.OOuWAnukVxxi6Ge.KV1MXHt0ymhokHu");

        users.add(user1);
        users.add(user2);
        userRpcService.batchAddUser(users);
    }
}
