package top.zuche.merchant.console.web.security.verifycode.repository;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;
import top.zuche.merchant.console.web.security.verifycode.VerifyCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Session存储
 *
 * @author lzx
 * @date 2019/5/10 下午11:39
 */
public class SessionVerifyCodeRepository implements VerifyCodeRepository {

    // 验证码在session中的名字
    private static final String VERIFY_CODE_SESSION_NAME = "S-VERIFY-CODE";

    @Override
    public void saveCode(VerifyCode code, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        if (code == null) {
            session.removeAttribute(VERIFY_CODE_SESSION_NAME);
        } else {
            code.eraseImage();
            session.setAttribute(VERIFY_CODE_SESSION_NAME, JSON.toJSONString(code));
        }
    }

    @Override
    public VerifyCode loadCode(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        String json = (String) session.getAttribute(VERIFY_CODE_SESSION_NAME);
        if (!StringUtils.hasLength(json)) {
            return null;
        }
        return JSON.parseObject(json, VerifyCode.class);
    }
}
