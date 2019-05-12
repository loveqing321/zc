package top.zuche.merchant.console;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.zuche.cache.annotation.EnableCache;

/**
 * @author lzx
 * @date 2019/5/7 下午11:46
 */
@SpringBootApplication
@EnableCache
@EnableDubboConfiguration
public class MerchantConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MerchantConsoleApplication.class, args);
    }
}
