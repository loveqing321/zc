package top.zuche.toolkit.web.security;

/**
 * 配置对象
 *
 * @author lzx
 * @date 2019/5/13 上午10:30
 */
public interface SecurityProperties {

    /**
     * token ttl.
     * @return
     */
    Integer getTokenTtl();

    /**
     * 获取权限上下文的存储key格式
     * @return
     */
    String getSecurityContextKeyFormat();
}
