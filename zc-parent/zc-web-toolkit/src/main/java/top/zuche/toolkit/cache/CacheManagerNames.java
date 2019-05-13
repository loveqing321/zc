package top.zuche.toolkit.cache;

/**
 * 缓存管理器名称列表
 *
 * @author lzx
 * @date 2019/5/8 上午12:01
 */
public interface CacheManagerNames {

    /**
     * redis缓存管理器
     */
    String REDIS_CACHE_MANAGER = "redisCacheManager";

    /**
     * ehcache缓存管理器
     */
    String EHCACHE_CACHE_MANAGER = "ehCacheCacheManager";

}
