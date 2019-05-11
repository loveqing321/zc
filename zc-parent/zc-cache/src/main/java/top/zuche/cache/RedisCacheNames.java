package top.zuche.cache;

/**
 * Redis缓存名，在RedisCacheManager中指定不同缓存名称的超时。
 *
 * @author lzx
 * @date 2019/5/7 下午11:48
 */
public interface RedisCacheNames {

    /**
     * 10分钟缓存名称
     */
    String CACHE_10MIN = "cache_10m";

    /**
     * 15分钟缓存名称
     */
    String CACHE_15MIN = "cache_15m";

    /**
     * 20分钟缓存名称
     */
    String CACHE_20MIN = "cache_20m";

    /**
     * 30分钟缓存名称
     */
    String CACHE_30MIN = "cache_30m";

    /**
     * 60分钟缓存名称
     */
    String CACHE_60MIN = "cache_60m";

    /**
     * 180分钟缓存名称
     */
    String CACHE_180MIN = "cache_180m";

}
