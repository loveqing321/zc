package top.zuche.cache.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author lzx
 * @date 2019/5/9 下午7:41
 */
public class UniqueIdGenerator {

    /**
     * 生成当天唯一的值，该值
     *
     * @param redisTemplate
     * @param prefix
     * @return
     */
    public static String generate(RedisTemplate<Object, Object> redisTemplate, String prefix) {
        String currentDate = "";
        String key = prefix + currentDate;
        long count = redisTemplate.opsForValue().increment(key, 1);
        if (count == 1) {
            redisTemplate.expire(key, 1, TimeUnit.DAYS);
        }
        return String.format("%s%s", key, String.format("%06d", count));
    }
}
