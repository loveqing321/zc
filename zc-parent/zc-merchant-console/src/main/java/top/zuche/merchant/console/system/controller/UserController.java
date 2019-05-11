package top.zuche.merchant.console.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.zuche.merchant.console.Constants;
import top.zuche.merchant.console.web.security.verifycode.VerifyCode;
import top.zuche.merchant.console.web.security.verifycode.repository.VerifyCodeRepository;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户控制器
 *
 * @author lzx
 * @date 2019/5/9 下午2:52
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    /**
     * Spring security 直接请求登录地址，屏蔽掉，提示通过首页跳转。
     * @return
     */
    @RequestMapping(value = "/getUserDetail", method = RequestMethod.GET)
    public void getUserDetail(HttpServletRequest request, HttpServletResponse response) {
        String contextPath = "/" + request.getServletContext().getContextPath();
        response.setContentType("text/html; charset=UTF-8");
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<body>");
        sb.append("<p>请直接访问首页 ");
        sb.append("<a href='").append(contextPath).append("'>");
        sb.append("Home");
        sb.append("</a></p>");
        sb.append("</body>");
        sb.append("</html>");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(sb.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
