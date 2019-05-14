package top.zuche.services.system.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import top.zuche.services.api.query.UserQuery;
import top.zuche.services.system.entity.UserEntity;

import java.util.List;

/**
 * 用户Mapper
 *
 * @author lzx
 * @date 2019/5/11 上午11:02
 */
public interface UserMapper {

    /**
     * 插入用户信息
     *
     * @param entity
     * @return
     */
    int insertUser(UserEntity entity);

    /**
     * 批量插入
     *
     * @param entities
     * @return
     */
    int batchInsertUser(List<UserEntity> entities);

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);

    /**
     * 根据用户账号删除
     *
     * @param username
     * @return
     */
    int deleteByUsername(String username);

    /**
     * 按照主键更新
     *
     * @param entity
     * @return
     */
    int updateUserByPrimaryKey(UserEntity entity);

    /**
     * 按照username更新
     *
     * @param entity
     * @return
     */
    int updateUserByUsername(UserEntity entity);

    /**
     * 根据用户名查找用户信息
     *
     * @param username
     * @return
     */
    UserEntity selectUserByUsername(@Param("username") String username);

    /**
     * 根据用户名查找带有角色信息的用户
     *
     * @param username
     * @return
     */
    UserEntity selectUserWithRolesByUsername(@Param("username") String username);

    /**
     * 根据用户名查找带有许可信息的用户
     *
     * @param username
     * @return
     */
    UserEntity selectUserWithPermissionsByUsername(@Param("username") String username);

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    Page<UserEntity> selectPageByCondition(UserQuery query);

}
