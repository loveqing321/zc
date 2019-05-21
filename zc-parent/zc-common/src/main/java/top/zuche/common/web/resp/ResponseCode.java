package top.zuche.common.web.resp;

import lombok.RequiredArgsConstructor;

/**
 * 响应的状态信息
 *
 * @author lzx
 * @date 2019/5/9 下午3:13
 */
@RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS(0, "成功"),

    // 认证类响应码范围 100 - 200
    UN_AUTHENTICATION(100, "用户未登录"),
    INVALID_USERNAME_OR_PASSWORD(101, "用户或密码错误"),
    LOCKED_ACCOUNT(102, "账户被锁定"),
    EXPIRED_PASSWORD(103, "密码过期"),
    EXPIRED_ACCOUNT(104, "账户过期"),
    DISABLED_ACCOUNT(105, "账户被禁用"),
    INVALID_VERIFY_CODE(106, "验证码无效"),
    EXPIRED_VERIFY_CODE(107, "验证码过期"),
    EXPIRED_TOKEN(108, "Token过期，请重新登录"),
    TOKEN_NOT_FOUND(109, "Token不存在，请先登录"),
    LOGIN_FAIL(199, "登录失败"),

    // 授权类响应码范围 200 - 300
    MISSING_CSRF_TOKEN(200, "缺少CSRF Token"),
    INVALID_CSRF_TOKEN(201, "CSRF Token无效"),

    ACCESS_DENIED(299, "拒绝访问"),

    // 业务类响应码范围 400 - 500
    SERVICE_ERROR(400, "服务调用异常"),

    BUSINESS_ERROR(499, "用户自定义业务异常"),   // 自定义业务异常一般会重写响应消息内容

    // 网络等其他异常 500 - 600
    NETWORK_ERROR(500, "网络异常，请重试"),
    SERVICE_TIMEOUT(501, "服务调用超时，请重试"),
    SERIALIZATION_ERROR(503, "序列化异常");

    public final int code;

    public final String message;

}
