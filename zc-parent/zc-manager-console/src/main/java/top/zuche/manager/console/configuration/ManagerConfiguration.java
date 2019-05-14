package top.zuche.manager.console.configuration;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.access.PermissionEvaluator;
import top.zuche.toolkit.web.security.SecurityProperties;
import top.zuche.toolkit.web.security.evaluator.PermissionEvaluatorImpl;

/**
 * 配置对象，用于生成该应用需要的bean
 *
 * @author lzx
 * @date 2019/5/10 下午12:13
 */
@Configuration
@EnableConfigurationProperties(ManagerProperties.class)
public class ManagerConfiguration {

    private RedisSerializer<String> keySerializer = StringRedisSerializer.UTF_8;

    private RedisSerializer<Object> valueSerializer = new GenericFastJsonRedisSerializer();

    @Bean
    @SuppressWarnings("unchecked")
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
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
    public PermissionEvaluator permissionEvaluator() {
        return new PermissionEvaluatorImpl();
    }

    @Bean
    public SecurityProperties securityProperties(ManagerProperties properties) {
        return new SecurityProperties() {
            @Override
            public Integer getTokenTtl() {
                return properties.getTokenTtl();
            }

            @Override
            public String getSecurityContextKeyFormat() {
                return "zc_security_context_manager_%s";
            }
        };
    }
}
