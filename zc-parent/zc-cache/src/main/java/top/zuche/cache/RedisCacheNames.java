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
    String CACHE_10MIN = "cache:10m";

    /**
     * 15分钟缓存名称
     */
    String CACHE_15MIN = "cache:15m";

    /**
     * 20分钟缓存名称
     */
    String CACHE_20MIN = "cache:20m";

    /**
     * 30分钟缓存名称
     */
    String CACHE_30MIN = "cache:30m";

    /**
     * 60分钟缓存名称
     */
    String CACHE_60MIN = "cache:60m";

    /**
     * 180分钟缓存名称
     */
    String CACHE_180MIN = "cache:180m";

}
