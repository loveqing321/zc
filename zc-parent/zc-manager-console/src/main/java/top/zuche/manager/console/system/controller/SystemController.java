package top.zuche.manager.console.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zuche.common.web.resp.ResponseCode;
import top.zuche.common.web.resp.ResponseData;
import top.zuche.toolkit.web.Constants;
import top.zuche.toolkit.web.security.verifycode.VerifyCode;
import top.zuche.toolkit.web.security.verifycode.repository.VerifyCodeRepository;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 系统控制器，处理登录等
 *
 * @author lzx
 * @date 2019/5/9 下午2:52
 */
@Controller
@Slf4j
public class SystemController {

    @Resource
    private VerifyCodeRepository verifyCodeRepository;

    /**
     * Spring security 直接请求登录地址，屏蔽掉，提示通过首页跳转。
     * @return
     */
    @RequestMapping(value = Constants.LOGIN_URL, method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) {
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

    /**
     * 请求验证码图片接口
     *
     * @return
     */
    @RequestMapping(value = Constants.VERIFY_CODE_URL, method = RequestMethod.GET)
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。

        try (ServletOutputStream sos = response.getOutputStream()) {
            // 生成验证码
            VerifyCode verifyCode = verifyCodeRepository.generateCode(request);
            // 获取图片
            BufferedImage image = verifyCode.getImage();
            // 保存验证码
            verifyCodeRepository.saveCode(verifyCode, request, response);
            // 写入响应流
            ImageIO.write(image, "jpeg", sos);
        } catch (IOException e) {
            log.error("生成验证码失败！", e);
        }
    }

    /**
     * 请求验证码图片接口
     *
     * @return
     */
    @PostMapping(value = Constants.ERROR_URL)
    @ResponseBody
    public ResponseData error(HttpServletRequest request, HttpServletResponse response) {
        return ResponseData.error(ResponseCode.SERVICE_ERROR);
    }
}
