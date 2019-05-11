package top.zuche.merchant.console.web.security.verifycode.repository;

import top.zuche.merchant.console.web.security.verifycode.VerifyCode;
import top.zuche.merchant.console.web.security.verifycode.VerifyCodeGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码存储
 *
 * @author lzx
 * @date 2019/5/10 下午11:36
 */
public interface VerifyCodeRepository {

    /**
     * 生成验证码
     *
     * @return 带有图片内容
     */
    default VerifyCode generateCode(HttpServletRequest request) {
        return VerifyCodeGenerator.generate();
    }

    /**
     * 保存验证码
     *
     * @param code
     * @param request
     * @param response
     */
    void saveCode(VerifyCode code, HttpServletRequest request, HttpServletResponse response);

    /**
     * 加载验证码
     *
     * @param request
     * @return
     */
    VerifyCode loadCode(HttpServletRequest request);

}
