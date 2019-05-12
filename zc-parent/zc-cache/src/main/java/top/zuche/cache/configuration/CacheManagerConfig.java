package top.zuche.cache.configuration;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import top.zuche.cache.CacheNames;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 缓存管理器配置对象，用于生成两个缓存管理器。
 *
 * @author lzx
 * @date 2019/5/7 下午11:58
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
public class CacheManagerConfig {

    private CacheProperties cacheProperties;

    private RedisConnectionFactory redisConnectionFactory;

    private RedisSerializer<String> keySerializer = StringRedisSerializer.UTF_8;

    private RedisSerializer<Object> valueSerializer = new GenericFastJsonRedisSerializer();

    public CacheManagerConfig(CacheProperties cacheProperties, RedisConnectionFactory redisConnectionFactory) {
        this.cacheProperties = cacheProperties;
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        // 构建不同缓存的缓存配置，缓存配置可以指定ttl时间，序列化方式等；
        Map<String, RedisCacheConfiguration> cacheConfigurations = redisCacheConfigurations();
        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }

    /**
     * 设置每个缓存的配置, 缓存名字在 RedisCacheNames 中设置
     *
     * @return
     */
    private Map<String, RedisCacheConfiguration> redisCacheConfigurations() {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        // 用户缓存 - 60m缓存
        cacheConfigurations.put(CacheNames.SYSTEM_USER_CACHE, RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
//                .disableCachingNullValues()  // 打开disableCachingNullValues的话，当需要缓存null值时，会报错，可以允许null值，通过unless来过滤
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer)));

        // 角色缓存 - 60m缓存
        cacheConfigurations.put(CacheNames.SYSTEM_ROLE_CACHE, RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer)));

        // 许可缓存 - 60m缓存
        cacheConfigurations.put(CacheNames.SYSTEM_PERMISSION_CACHE, RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer)));

        return cacheConfigurations;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        // 获取配置的EhCache配置文件路径，构建EhCache管理器
        Resource location = this.cacheProperties.resolveConfigLocation(this.cacheProperties.getEhcache().getConfig());
        return new EhCacheCacheManager(EhCacheManagerUtils.buildCacheManager(location));
    }

    /**
     * 使用CachingConfigurer指定默认的缓存管理器
     * @return
     */
    @Bean
    public CachingConfigurer cachingConfigurer(RedisCacheManager redisCacheManager) {

        return new CachingConfigurerSupport() {
            @Override
            public CacheManager cacheManager() {
                return redisCacheManager;
            }

            @Override
            public CacheErrorHandler errorHandler() {
                return new LoggingCacheErrorHandler();
            }
        };
    }

    /**
     * 记录缓存出错信息
     */
    @Slf4j
    private static class LoggingCacheErrorHandler extends SimpleCacheErrorHandler {

        @Override
        public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
            log.error("Cache get error. Cache name: {}, key: {}, exception: {}", cache == null ? "unknown" : cache.getName(), key.toString(), e.getMessage());
            super.handleCacheGetError(e, cache, key);
        }

        @Override
        public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
            log.error("Cache put error. Cache name: {}, key: {}, exception: {}", cache == null ? "unknown" : cache.getName(), key.toString(), e.getMessage());
            super.handleCachePutError(e, cache, key, value);
        }

        @Override
        public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
            log.error("Cache evict error. Cache name: {}, key: {}, exception: {}", cache == null ? "unknown" : cache.getName(), key.toString(), e.getMessage());
            super.handleCacheEvictError(e, cache, key);
        }

        @Override
        public void handleCacheClearError(RuntimeException e, Cache cache) {
            log.error("Cache clear error. Cache name: {}, exception: {}", cache == null ? "unknown" : cache.getName(), e.getMessage());
            super.handleCacheClearError(e, cache);
        }
    }
}
