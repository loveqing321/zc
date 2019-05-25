package top.zuche.toolkit.web.security.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import top.zuche.toolkit.web.Constants;
import top.zuche.toolkit.web.security.SecurityProperties;
import top.zuche.toolkit.web.security.configurer.TokenAccessConfigurer;
import top.zuche.toolkit.web.security.configurer.VerifyCodeConfigurer;
import top.zuche.toolkit.web.security.context.RedisSecurityContextRepository;
import top.zuche.toolkit.web.security.handler.AccessDeniedHandlerImpl;
import top.zuche.toolkit.web.security.handler.AuthenticationFailureHandlerImpl;
import top.zuche.toolkit.web.security.handler.AuthenticationSuccessHandlerImpl;
import top.zuche.toolkit.web.security.handler.LogoutSuccessHandlerImpl;
import top.zuche.toolkit.web.security.verifycode.repository.CookieVerifyCodeRepository;
import top.zuche.toolkit.web.security.verifycode.repository.VerifyCodeRepository;

/**
 * @author lzx
 * @date 2019/5/9 上午10:51
 */
@Configuration
@EnableWebSecurity // 启用springboot默认的security配置
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 启用方法层面的权限控制 启用@PreAuthorize @PostAuthorize @PreFilter @PostFilter
@ConditionalOnBean(value = { SecurityProperties.class, RedisTemplate.class, UserDetailsService.class })
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private SecurityProperties securityProperties;

    private RedisTemplate redisTemplate;

    private UserDetailsService userDetailsService;

    public WebSecurityConfiguration(SecurityProperties securityProperties, RedisTemplate redisTemplate, UserDetailsService userDetailsService) {
        this.securityProperties = securityProperties;
        this.redisTemplate = redisTemplate;
        this.userDetailsService = userDetailsService;
    }

    /**
     * 配置认证管理器Builder
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 配置 Http权限
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 基于Redis的token权限上下文存储
        RedisSecurityContextRepository securityContextRepository = new RedisSecurityContextRepository(redisTemplate, securityProperties);

        http.securityContext().securityContextRepository(securityContextRepository)
                .and()
                .authorizeRequests()
                .antMatchers("/", Constants.LOGIN_URL, Constants.VERIFY_CODE_URL).permitAll()   // 过滤一些不需要权限控制的地址，忽略登录页，保证不会重复重定向。
                .anyRequest().authenticated()  // 其他地址都需要登录认证
                .and()
                .apply(new VerifyCodeConfigurer<>()).loginUrl(Constants.LOGIN_URL).verifyCodeParameter("verifyCode")  // 验证码过滤器
                .failureHandler(new AuthenticationFailureHandlerImpl())  // 指定验证码失败处理
                .and()
                .apply(new TokenAccessConfigurer<>()).loginUrl(Constants.LOGIN_URL).ignoreUrls(Constants.VERIFY_CODE_URL)
                .usernameParameter("username").passwordParameter("password")  // 指定表单登录的用户名密码参数
                .failureHandler(new AuthenticationFailureHandlerImpl())  // 指定登录失败处理
                .successHandler(new AuthenticationSuccessHandlerImpl())  // 指定登录成功处理
                .and()
                .logout().logoutUrl(Constants.LOGOUT_URL).logoutSuccessHandler(new LogoutSuccessHandlerImpl()).permitAll()  // 退出不需要做权限控制
                .and()
                .exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl()) // 异常处理
                .and()
                .csrf().ignoringAntMatchers(Constants.LOGIN_URL).csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .cors();
    }

    /**
     * 配置 静态资源等控制
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("index.html", "/static/**", "/favicon.ico");
    }

    /**
     * 跨域配置，会被Spring Security使用到
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(CorsConfiguration.class)
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

    /**
     * 验证码存储
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(VerifyCodeRepository.class)
    public VerifyCodeRepository verifyCodeRepository() {
        return new CookieVerifyCodeRepository();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode("admin");
        System.out.println(encoded);
        boolean flag = encoder.matches("admin", encoded);
        System.out.println(flag);
    }
}
