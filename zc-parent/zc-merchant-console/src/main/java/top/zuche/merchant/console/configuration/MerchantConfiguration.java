package top.zuche.merchant.console.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import top.zuche.merchant.console.Constants;
import top.zuche.toolkit.web.security.SecurityProperties;
import top.zuche.toolkit.web.security.evaluator.PermissionEvaluatorImpl;
import top.zuche.toolkit.web.security.verifycode.repository.CookieVerifyCodeRepository;
import top.zuche.toolkit.web.security.verifycode.repository.VerifyCodeRepository;

/**
 * 配置对象，用于生成该应用需要的bean
 *
 * @author lzx
 * @date 2019/5/10 下午12:13
 */
@Configuration
@EnableConfigurationProperties(MerchantProperties.class)
public class MerchantConfiguration {

    /**
     * 跨域配置，会被Spring Security使用到
     *
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(Constants.LOGIN_URL, corsConfiguration);
        source.registerCorsConfiguration(Constants.LOGOUT_URL, corsConfiguration);
        source.registerCorsConfiguration("/api/**", corsConfiguration);
        return source;
    }

    @Bean
    public VerifyCodeRepository verifyCodeRepository() {
        return new CookieVerifyCodeRepository();
    }

    @Bean
    public PermissionEvaluator permissionEvaluator() {
        return new PermissionEvaluatorImpl();
    }

    @Bean
    public SecurityProperties securityProperties(MerchantProperties properties) {
        return new SecurityProperties() {
            @Override
            public Integer getTokenTtl() {
                return properties.getTokenTtl();
            }

            @Override
            public String getSecurityContextKeyFormat() {
                return "zc_security_context_%s";
            }
        };
    }
}
