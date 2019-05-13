package top.zuche.toolkit.annotation;

import org.springframework.context.annotation.Import;
import top.zuche.toolkit.web.advice.GenericControllerAdviceSelector;

import java.lang.annotation.*;

/**
 * @author lzx
 * @date 2019/5/8 下午2:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GenericControllerAdviceSelector.class)
public @interface EnableGenericAdvice {

}
