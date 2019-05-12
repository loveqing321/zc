package top.zuche.services.system.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import top.zuche.services.api.dto.UserDTO;
import top.zuche.services.api.exception.ServiceException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzx
 * @date 2019/5/11 下午12:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class PermissionServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void addUser() throws ServiceException {
        UserDTO user = new UserDTO();
        user.setMerchantId(1);
        user.setUsername("13333201150");
        user.setDepartment("开发");
        user.setPost("好的");
        user.setCname("刘振兴");
        user.setPassword("$2a$10$LbAO/q6OlhB8KXkxlwLbf.OOuWAnukVxxi6Ge.KV1MXHt0ymhokHu");
        userService.addUser(user);
        System.out.println(user.getId());
    }

    @Test
    public void batchAddUser() throws ServiceException {
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
        userService.batchAddUser(users);
    }

    @Test
    public void queryUserByUsername() throws ServiceException {
        UserDTO user = userService.queryUserByUsername("13333201150");
        Assert.assertNotNull(user);
        Assert.assertEquals("13333201150", user.getUsername());
    }

    @Test
    public void queryUserWithRolesByUsername() throws ServiceException {
        UserDTO user = userService.queryUserWithRolesByUsername("13333201150");
        Assert.assertNotNull(user);
        Assert.assertEquals("13333201150", user.getUsername());
    }

    @Test
    public void queryUserWithPermissionsByUsername() throws ServiceException {
        UserDTO user = userService.queryUserWithPermissionsByUsername("13333201150");
        Assert.assertNotNull(user);
        Assert.assertEquals("13333201150", user.getUsername());
    }

    @Test
    public void updateUserByPrimaryKey() throws ServiceException {
        UserDTO user = new UserDTO();
        user.setId(13);
        user.setMerchantId(1);
        user.setUsername("13333201150");
        user.setDepartment("开发");
        user.setPost("好的11");
        user.setCname("刘振兴");
        user.setPassword("$2a$10$LbAO/q6OlhB8KXkxlwLbf.OOuWAnukVxxi6Ge.KV1MXHt0ymhokHu");
        userService.updateUserByPrimaryKey(user);
    }

    @Test
    public void updateUserByUsername() throws ServiceException {
        UserDTO user = new UserDTO();
        user.setId(13);
        user.setMerchantId(1);
        user.setUsername("13333201150");
        user.setDepartment("开发");
        user.setPost("好的22");
        user.setCname("刘振兴");
        user.setPassword("$2a$10$LbAO/q6OlhB8KXkxlwLbf.OOuWAnukVxxi6Ge.KV1MXHt0ymhokHu");
        userService.updateUserByUsername(user);
    }

    @Test
    public void deleteByPrimaryKey() throws ServiceException {
        userService.deleteByPrimaryKey(13);
    }

    @Test
    public void deleteByUsername() throws ServiceException {
        userService.deleteByUsername("13333201150");
    }
}