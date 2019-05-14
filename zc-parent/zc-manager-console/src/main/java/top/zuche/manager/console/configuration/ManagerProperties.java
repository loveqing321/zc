package top.zuche.manager.console.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lzx
 * @date 2019/5/13 上午12:00
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "zc.manager")
public class ManagerProperties {

    // token的超时，单位分钟
    private Integer tokenTtl;

}
