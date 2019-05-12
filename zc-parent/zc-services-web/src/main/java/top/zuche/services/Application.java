package top.zuche.services;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.zuche.cache.annotation.EnableCache;

/**
 * @author lzx
 * @date 2019/5/8 下午1:08
 */
@SpringBootApplication(scanBasePackages = {"top.zuche.services"})
@MapperScan(basePackages = {"top.zuche.services.system.mapper",
        "top.zuche.services.basic.mapper",
        "top.zuche.services.order.mapper",
        "top.zuche.services.payment.mapper",
        "top.zuche.services.report.mapper"})
@EnableCache
@EnableDubboConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
