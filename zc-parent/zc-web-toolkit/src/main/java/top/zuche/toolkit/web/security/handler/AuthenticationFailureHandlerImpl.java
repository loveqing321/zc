package top.zuche.toolkit.web.security.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import top.zuche.common.web.resp.ResponseCode;
import top.zuche.common.web.resp.ResponseData;
import top.zuche.toolkit.web.security.exception.InvalidVerifyCodeException;
import top.zuche.toolkit.web.security.exception.TokenExpiredException;
import top.zuche.toolkit.web.security.exception.TokenNotFoundException;
import top.zuche.toolkit.web.security.exception.VerifyCodeExpiredException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证失败处理器，返回json格式的数据
 *
 * @author lzx
 * @date 2019/5/9 下午3:06
 */
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResponseData responseData;
        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
            responseData = ResponseData.error(ResponseCode.INVALID_USERNAME_OR_PASSWORD);
        } else if (exception instanceof LockedException) {
            responseData = ResponseData.error(ResponseCode.LOCKED_ACCOUNT);
        } else if (exception instanceof CredentialsExpiredException) {
            responseData = ResponseData.error(ResponseCode.EXPIRED_PASSWORD);
        } else if (exception instanceof AccountExpiredException) {
            responseData = ResponseData.error(ResponseCode.EXPIRED_ACCOUNT);
        } else if (exception instanceof DisabledException) {
            responseData = ResponseData.error(ResponseCode.DISABLED_ACCOUNT);
        } else if (exception instanceof TokenExpiredException) {
            responseData = ResponseData.error(ResponseCode.EXPIRED_TOKEN);
        } else if (exception instanceof TokenNotFoundException) {
            responseData = ResponseData.error(ResponseCode.TOKEN_NOT_FOUND);
        } else if (exception instanceof InvalidVerifyCodeException) {
            responseData = ResponseData.error(ResponseCode.INVALID_VERIFY_CODE);
        } else if (exception instanceof VerifyCodeExpiredException) {
            responseData = ResponseData.error(ResponseCode.EXPIRED_VERIFY_CODE);
        } else {
            responseData = ResponseData.error(ResponseCode.LOGIN_FAIL);
        }
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(responseData));
        writer.flush();
        writer.close();
    }
}
