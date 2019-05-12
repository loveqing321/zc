package top.zuche.merchant.console.system.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lzx
 * @date 2019/5/12 上午9:14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserDetailsServiceImplTest {

    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void loadUserByUsername() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("13333201150");
        System.out.println(userDetails);
    }

}