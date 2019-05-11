package top.zuche.merchant.console.web.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import top.zuche.common.web.resp.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证成功处理器
 *
 * @author lzx
 * @date 2019/5/9 下午3:35
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        // 获取认证后的 principal 对象，在指定 DaoAuthenticationProvider 的 forcePrincipalAsString 默认是false，会使用完整的UserDetails对象。
        TokenAuthenticationToken tat = (TokenAuthenticationToken) authentication;
        String token = tat.getToken();
        Object principal = authentication.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("principal", principal);
        ResponseData responseData = ResponseData.ok(map);
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(responseData));
        writer.flush();
        writer.close();
    }
}
