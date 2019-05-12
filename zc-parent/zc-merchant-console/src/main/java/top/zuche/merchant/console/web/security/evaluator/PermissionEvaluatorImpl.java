package top.zuche.merchant.console.web.security.evaluator;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * 许可判断逻辑
 * @EnableGlobalMethodSecurity 注解会是能 GlobalMethodSecurityConfiguration
 * @author lzx
 * @date 2019/5/13 上午12:44
 */
public class PermissionEvaluatorImpl implements PermissionEvaluator {

    

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        System.out.println(targetDomainObject);
        System.out.println(permission);

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
