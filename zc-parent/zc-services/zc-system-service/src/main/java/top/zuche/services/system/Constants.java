package top.zuche.services.system;

/**
 * 常量表
 *
 * @author lzx
 * @date 2019/5/11 上午11:58
 */
public interface Constants {

    /**
     * 服务异常提示信息
     */
    interface ServiceMessage {

        String LOSE_USE_ID = "缺少用户ID";

        String LOSE_USE_NAME = "缺少用户账号";

        String EXISTS_USER_NAME = "用户名已经存在";

        String EMPTY_USER_NAME = "用户名不能为空";

        String UPDATE_USER_FAIL = "更新用户失败";

        String UN_EXISTS_USER_ID = "不存在该用户ID";

        String UN_EXISTS_USER_NAME = "不存在该用户账号";

    }

}
