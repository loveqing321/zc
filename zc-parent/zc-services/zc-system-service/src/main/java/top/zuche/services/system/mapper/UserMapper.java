package top.zuche.services.system.mapper;

import org.apache.ibatis.annotations.Param;
import top.zuche.services.api.dto.UserDTO;

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
     * @param user
     * @return
     */
    int insertUser(UserDTO user);

    /**
     * 批量插入
     *
     * @param users
     * @return
     */
    int batchInsertUser(List<UserDTO> users);

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
     * @param user
     * @return
     */
    int updateUserByPrimaryKey(UserDTO user);

    /**
     * 按照username更新
     *
     * @param user
     * @return
     */
    int updateUserByUsername(UserDTO user);

    /**
     * 根据用户名查找用户信息
     *
     * @param username
     * @return
     */
    UserDTO selectUserByUsername(@Param("username") String username);

    /**
     * 根据用户名查找带有角色信息的用户
     *
     * @param username
     * @return
     */
    UserDTO selectUserWithRolesByUsername(@Param("username") String username);

}
