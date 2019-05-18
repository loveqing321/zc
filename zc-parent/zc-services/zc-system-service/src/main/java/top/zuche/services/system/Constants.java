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

        // 用户服务
        String LOSE_USE_ID = "缺少用户ID";

        String LOSE_USER_NAME = "缺少用户账号";

        String EXISTS_USER_NAME = "用户名已经存在";

        String EMPTY_USER_NAME = "用户名不能为空";

        String UPDATE_USER_FAIL = "更新用户失败";

        String UN_EXISTS_USER_ID = "不存在该用户ID";

        String UN_EXISTS_USER_NAME = "不存在该用户账号";

        // 角色服务
        String EMPTY_ROLE_NAME = "角色名不能为空";

        String EXISTS_ROLE_NAME = "角色名已经存在";

        String LOSE_ROLE_ID = "缺少角色ID";

        String LOSE_ROLE_NAME = "缺少角色名称";

        String UPDATE_ROLE_FAIL = "更新角色失败";

        String UN_EXISTS_ROLE_ID = "不存在该角色ID";

        String UN_EXISTS_ROLE_NAME = "不存在该角色";

        // 许可服务
        String EMPTY_PERMISSION_NAME = "许可名不能为空";

        String EXISTS_PERMISSION_NAME = "许可名/标签已经存在";

        String LOSE_PERMISSION_ID = "缺少许可ID";

        String LOSE_PERMISSION_NAME = "缺少许可名称";

        String UPDATE_PERMISSION_FAIL = "更新许可失败";

        String UN_EXISTS_PERMISSION_ID = "不存在该许可ID";

        String UN_EXISTS_PERMISSION_NAME = "不存在该许可";

        // 字典服务
        String EMPTY_DICT_TYPE = "字典类型不能为空";

        String EMPTY_DICT_CODE = "字典编码不能为空";

        String EMPTY_DICT_VALUE = "字典值不能为空";

        String EXISTS_DICT_TYPE_CODE = "字典类型和字典编码已经存在";

        String LOSE_DICT_ID = "缺少字典ID";

        String UPDATE_DICT_FAIL = "更新字典值失败";

        String UN_EXISTS_DICT_ID = "不存在该字典ID";

    }

}
