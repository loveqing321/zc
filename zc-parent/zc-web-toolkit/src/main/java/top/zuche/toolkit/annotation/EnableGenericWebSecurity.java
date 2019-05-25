package top.zuche.toolkit.annotation;

import org.springframework.context.annotation.Import;
import top.zuche.toolkit.web.security.configuration.GenericWebSecuritySelector;
import top.zuche.toolkit.web.security.configuration.WebSecurityConfiguration;

import java.lang.annotation.*;

/**
 * @author lzx
 * @date 2019/5/8 下午2:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WebSecurityConfiguration.class)
public @interface EnableGenericWebSecurity {

}
