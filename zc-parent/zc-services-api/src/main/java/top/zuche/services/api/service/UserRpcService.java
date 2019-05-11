package top.zuche.services.api.service;

import top.zuche.services.api.dto.UserDTO;

/**
 * 用户服务
 *
 * @author lzx
 * @date 2019/5/9 下午8:19
 */
public interface UserRpcService {

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    UserDTO userLogin(String username, String password);

}
