package top.zuche.toolkit.web.security.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import top.zuche.common.web.resp.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 成功退出
 *
 * @author lzx
 * @date 2019/5/9 下午6:26
 */
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        // 获取认证后的 principal 对象，在指定 DaoAuthenticationProvider 的 forcePrincipalAsString 默认是false，会使用完整的UserDetails对象。
        Object principal = authentication.getPrincipal();
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ResponseData.ok()));
        writer.flush();
        writer.close();
    }
}
