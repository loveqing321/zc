package top.zuche.cache.annotation;

import org.springframework.context.annotation.Import;
import top.zuche.cache.configuration.CacheManagerSelector;

import java.lang.annotation.*;

/**
 * @author lzx
 * @date 2019/5/8 下午2:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CacheManagerSelector.class)
public @interface EnableCache {

}
