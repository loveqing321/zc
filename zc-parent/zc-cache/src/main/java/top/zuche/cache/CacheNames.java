package top.zuche.cache;

/**
 * 缓存名称，注意ehcache 和 redis的配置方式不同
 *
 * @author lzx
 * @date 2019/5/13 上午1:50
 */
public interface CacheNames {

    // 系统模块/用户缓存
    String SYSTEM_USER_CACHE = "zc_system_user";

    // 系统模块/角色缓存
    String SYSTEM_ROLE_CACHE = "zc_system_role";

    // 系统模块/许可缓存
    String SYSTEM_PERMISSION_CACHE = "zc_system_permission";

}
