package top.zuche.merchant.console.web.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import top.zuche.common.web.resp.ResponseCode;
import top.zuche.common.web.resp.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 访问拒绝处理器
 *
 * @author lzx
 * @date 2019/5/10 下午1:35
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResponseData responseData;
        if (exception instanceof MissingCsrfTokenException) {
            responseData = ResponseData.error(ResponseCode.MISSING_CSRF_TOKEN);
        } else if (exception instanceof InvalidCsrfTokenException) {
            responseData = ResponseData.error(ResponseCode.INVALID_CSRF_TOKEN);
        } else {
            responseData = ResponseData.error(ResponseCode.ACCESS_DENIED);
        }
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(responseData));
        writer.flush();
        writer.close();
    }
}
