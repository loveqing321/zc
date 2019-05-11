package top.zuche.services.system.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import top.zuche.services.api.dto.UserDTO;
import top.zuche.services.api.service.UserRpcService;

/**
 * 用户服务
 *
 * @author lzx
 * @date 2019/5/9 下午9:16
 */
@Service(interfaceClass = UserRpcService.class)
@Component("userService")
public class UserService implements UserRpcService {

    /**
     * 使用用户名密码登录，如果登录成功，则返回包装的用户对象
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public UserDTO userLogin(String username, String password) {


        return null;
    }
}
