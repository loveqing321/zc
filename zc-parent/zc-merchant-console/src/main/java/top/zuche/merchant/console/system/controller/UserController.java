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
@RequestMapping("/api/user")
public class UserController {

}
