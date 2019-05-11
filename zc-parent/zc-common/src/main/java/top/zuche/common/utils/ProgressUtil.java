package top.zuche.common.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * 进程工具类
 * @author lzx
 * @date 2019/5/11 下午3:24
 */
public class ProgressUtil {
    // 进程号
    private static int PID = -1;

    /**
     * 获取进程号
     * @return
     */
    public static int getPid() {
        if (PID < 0) {
            try {
                RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
                String name = runtime.getName(); // format: "pid@hostname"
                PID = Integer.parseInt(name.substring(0, name.indexOf('@')));
            } catch (Throwable e) {
                PID = 0;
            }
        }
        return PID;
    }
}
