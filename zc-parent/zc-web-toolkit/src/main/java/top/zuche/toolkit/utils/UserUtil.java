package top.zuche.toolkit.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
    public static UserDetails getCurrentUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
