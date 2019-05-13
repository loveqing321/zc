package top.zuche.toolkit.utils;

import top.zuche.common.utils.RandomUtil;
import top.zuche.toolkit.web.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * token工具类
 *
 * @author lzx
 * @date 2019/5/9 下午10:28
 */
public class TokenUtil {

    /**
     * 获取请求中携带的token
     *
     * @param request
     * @return
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(Constants.TOKEN_NAME);
        if (token == null) {
            token = request.getParameter(Constants.TOKEN_NAME);
        }
        return token;
    }

    /**
     * 生成token
     *
     * @return
     */
    public static String generateNew() {
        return RandomUtil.uuid();
    }
}
