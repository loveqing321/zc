package top.zuche.toolkit.web.https;

import io.undertow.servlet.api.SecurityConstraint;
import io.undertow.servlet.api.SecurityInfo;
import io.undertow.servlet.api.TransportGuaranteeType;
import io.undertow.servlet.api.WebResourceCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Https启用配置
 *
 * @author lzx
 * @date 2019/5/22 下午5:05
 */
@Configuration
public class HttpsWebServerConfiguration {

    @Value("${server.http.port:8080}")
    private int httpPort;

    @Value("${server.port:443}")
    private int httpsPort;

    @Bean
    public ServletWebServerFactory undertowWebServerFactory() {
        UndertowServletWebServerFactory undertowServletWebServerFactory = new UndertowServletWebServerFactory();
        undertowServletWebServerFactory.addBuilderCustomizers(builder -> {
            builder.addHttpListener(httpPort, "0.0.0.0");
        });
        undertowServletWebServerFactory.addDeploymentInfoCustomizers(deploymentInfo -> {
            SecurityConstraint securityConstraint = new SecurityConstraint();
            securityConstraint.addWebResourceCollection(new WebResourceCollection().addUrlPattern("/"));
            securityConstraint.setTransportGuaranteeType(TransportGuaranteeType.CONFIDENTIAL);
            securityConstraint.setEmptyRoleSemantic(SecurityInfo.EmptyRoleSemantic.PERMIT);
            deploymentInfo.addSecurityConstraint(securityConstraint).setConfidentialPortManager(exchange -> httpsPort);
        });
        return undertowServletWebServerFactory;
    }
}
