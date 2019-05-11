package top.zuche.common.utils;

import java.util.UUID;

/**
 * 随机工具类
 * @author lzx
 * @date 2019/5/9 下午10:21
 */
public class RandomUtil {

    /**
     * 生成uuid，不带中间的横线
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
