package top.zuche.manager.console;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import top.zuche.toolkit.web.https.HttpsWebServerConfiguration;
import top.zuche.toolkit.web.security.configuration.WebSecurityConfiguration;

/**
 * @author lzx
 * @date 2019/5/7 下午11:46
 */
@SpringBootApplication
@EnableDubboConfiguration
@Import({
        WebSecurityConfiguration.class,
        // 缓存管理
//        CacheManagerConfig.class,
        // https支持
//        HttpsWebServerConfiguration.class
})
public class ManagerConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerConsoleApplication.class, args);
    }
}
