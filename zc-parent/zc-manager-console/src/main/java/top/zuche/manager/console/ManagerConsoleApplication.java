package top.zuche.manager.console;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.zuche.toolkit.annotation.EnableGenericAdvice;
import top.zuche.toolkit.annotation.EnableGenericWebSecurity;

/**
 * @author lzx
 * @date 2019/5/7 下午11:46
 */
@SpringBootApplication
@EnableDubboConfiguration
//@EnableCache
@EnableGenericAdvice
@EnableGenericWebSecurity
public class ManagerConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerConsoleApplication.class, args);
    }
}
