package top.zuche.cache;

/**
 * 在ehcache中配置与名称对应的缓存，在缓存配置中指定超时时间
 *
 * @author lzx
 * @date 2019/5/7 下午11:52
 */
public interface EhCacheNames {

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
