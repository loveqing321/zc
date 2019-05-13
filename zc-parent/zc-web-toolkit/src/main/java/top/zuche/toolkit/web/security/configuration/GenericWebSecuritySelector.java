package top.zuche.toolkit.web.security.configuration;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 选择加载 WebSecurityConfiguration
 * @author lzx
 * @date 2019/5/8 下午2:07
 */
public class GenericWebSecuritySelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{WebSecurityConfiguration.class.getName()};
    }
}
