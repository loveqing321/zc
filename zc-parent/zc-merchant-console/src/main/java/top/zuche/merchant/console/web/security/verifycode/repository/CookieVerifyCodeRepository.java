package top.zuche.merchant.console.web.security.verifycode.repository;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;
import top.zuche.merchant.console.web.security.verifycode.VerifyCode;
import top.zuche.merchant.console.web.security.verifycode.VerifyCodeGenerator;
import top.zuche.merchant.console.web.security.verifycode.repository.VerifyCodeRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cookie存储
 *
 * @author lzx
 * @date 2019/5/10 下午11:39
 */
public class CookieVerifyCodeRepository implements VerifyCodeRepository {

    // 验证码在cookie中的名字
    private static final String VERIFY_CODE_COOKIE_NAME = "C-VERIFY-CODE";

    @Override
    public void saveCode(VerifyCode code, HttpServletRequest request, HttpServletResponse response) {
        String value = "";
        if (code != null) {
            code.eraseImage();
            try {
                value = URLEncoder.encode(JSON.toJSONString(code), "utf-8");
            } catch (UnsupportedEncodingException e) {
                // ignore.
            }
        }
        Cookie cookie = new Cookie(VERIFY_CODE_COOKIE_NAME, value);
        cookie.setPath(this.getRequestContext(request));
        if (code == null) {
            cookie.setMaxAge(0);
        } else {
            cookie.setMaxAge(-1);
        }
        response.addCookie(cookie);
    }

    @Override
    public VerifyCode loadCode(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, VERIFY_CODE_COOKIE_NAME);
        if (cookie == null) {
            return null;
        }
        String json = cookie.getValue();
        if (!StringUtils.hasLength(json)) {
            return null;
        }
        try {
            json = URLDecoder.decode(json, "utf-8");
            return JSON.parseObject(json, VerifyCode.class);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    private String getRequestContext(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return contextPath.length() > 0 ? contextPath : "/";
    }
}
