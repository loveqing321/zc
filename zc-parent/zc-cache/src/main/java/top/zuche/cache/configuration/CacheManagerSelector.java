package top.zuche.cache.configuration;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 选择加载 CacheManagerConfig
 * @author lzx
 * @date 2019/5/8 下午2:07
 */
public class CacheManagerSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{CacheManagerConfig.class.getName()};
    }
}
