package top.zuche.merchant.console.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import top.zuche.merchant.console.system.dto.UserDetailsImpl;
import top.zuche.services.api.dto.UserDTO;

/**
 * 用户工具类
 *
 * @author lzx
 * @date 2019/5/12 下午10:50
 */
public class UserUtil {

    /**
     * 获取当前用户
     *
     * @return
     */
    public static UserDetailsImpl getCurrentUser() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
