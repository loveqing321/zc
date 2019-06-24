package top.zuche.common.utils;

/**
 * 脱敏工具类
 * 支持三种脱敏方式，和常用脱敏方法
 * 1. 保留前n位
 * 2. 保留后m位
 * 3. 保留前n位 + 后m位
 *
 * @author lzx
 * @date 2019/6/11 4:04 PM
 */
public class ConcealUtil {

    private static String PLACEHOLDER = "*";

    /**
     * 对后m位进行脱敏
     *
     * @param original 原始字符串
     * @param m 后m位
     * @return
     */
    public static String concealRight(String original, int m) {
        if (original == null) return null;
        int len = original.length();
        if (len <= m) return original;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<len-m; i++) {
            sb.append(original.indexOf(i));
        }
        for (int i=0; i<m; i++) {
            sb.append(PLACEHOLDER);
        }
        return sb.toString();
    }

    /**
     * 对前n位进行脱敏
     *
     * @param original 原始字符串
     * @param n 前n位
     * @return
     */
    public static String concealLeft(String original, int n) {
        if (original == null) return null;
        int len = original.length();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<len && i<n; i++) {
            sb.append(PLACEHOLDER);
        }
        if (len <= n) return sb.toString();
        for (int i=len-n; i<len; i++) {
            sb.append(original.indexOf(i));
        }
        return sb.toString();
    }

    /**
     * 对中间的进行脱敏
     *
     * @param original 原始字符串
     * @param start 开始位 第几个数字 从1开始
     * @param end 结束位 第几个数字 从1开始
     * @return
     */
    public static String concealMiddle(String original, int start, int end) {
        if (original == null) return null;
        int len = original.length();
        if (start < 1) {
            start = 1;
        }
        if (end > len) {
            end = len;
        }
        // 没达到开始位置
        if (len < start) return original;
        // 如果没有超过结束位置，则直接对右侧进行脱敏
        if (len <= end) return concealRight(original, len-start);

        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=len; i++) {
            if (i < start || i > end) {
                sb.append(original.indexOf(i-1));
            } else {
                sb.append(PLACEHOLDER);
            }
        }
        return sb.toString();
    }

    /**
     * 脱敏电话号码，脱敏3-8位
     *
     * @param phoneNumber
     * @return
     */
    public static String concealPhoneNumber(String phoneNumber) {
        return concealMiddle(phoneNumber, 3, 8);
    }
}
