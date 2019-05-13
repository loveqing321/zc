package top.zuche.toolkit.web.advice;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 选择加载 GenericControllerAdvice
 * @author lzx
 * @date 2019/5/8 下午2:07
 */
public class GenericControllerAdviceSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{GenericControllerAdvice.class.getName()};
    }
}
