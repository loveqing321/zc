package top.zuche.services.system.mapper;

import org.apache.ibatis.annotations.Param;
import top.zuche.services.api.dto.UserDTO;
import top.zuche.services.system.entity.RoleEntity;

import java.util.List;

/**
 * 用户Mapper
 *
 * @author lzx
 * @date 2019/5/11 上午11:02
 */
public interface RoleMapper {

    /**
     * 插入信息
     *
     * @param entity
     * @return
     */
    int insertRole(RoleEntity entity);

    /**
     * 批量插入
     *
     * @param users
     * @return
     */
    int batchInsertRole(List<RoleEntity> users);

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);

    /**
     * 根据角色名删除
     *
     * @param roleName
     * @return
     */
    int deleteByRoleName(String roleName);

    /**
     * 按照主键更新
     *
     * @param entity
     * @return
     */
    int updateRoleByPrimaryKey(RoleEntity entity);

    /**
     * 根据id查找信息
     *
     * @param id
     * @return
     */
    RoleEntity selectRoleById(@Param("id") int id);

    /**
     * 根据用户名查找该用户的所有角色
     *
     * @param username
     * @return
     */
    List<RoleEntity> selectRolesByUsername(@Param("username") String username);

}