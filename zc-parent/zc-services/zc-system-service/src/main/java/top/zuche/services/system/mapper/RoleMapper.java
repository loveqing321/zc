package top.zuche.services.system.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import top.zuche.services.api.dto.UserDTO;
import top.zuche.services.api.query.RoleQuery;
import top.zuche.services.system.entity.RoleEntity;

import java.util.List;

/**
 * 角色Mapper
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
     * 按照角色名更新
     *
     * @param entity
     * @return
     */
    int updateRoleByRoleName(RoleEntity entity);

    /**
     * 根据角色ID查找信息
     *
     * @param id
     * @return
     */
    RoleEntity selectByPrimaryKey(@Param("id") int id);

    /**
     * 根据角色名字查找信息
     *
     * @param roleName
     * @return
     */
    RoleEntity selectRoleByRoleName(@Param("roleName") String roleName);

    /**
     * 根据角色名字查找信息，携带权限列表
     *
     * @param roleName
     * @return
     */
    RoleEntity selectRoleWithPermissionsByRoleName(@Param("roleName") String roleName);

    /**
     * 根据用户名查找该用户的所有角色
     *
     * @param username
     * @return
     */
    List<RoleEntity> selectRolesByUsername(@Param("username") String username);

    /**
     * 根据条件分页查询
     *
     * @param query
     * @return
     */
    Page<RoleEntity> selectPageByCondition(RoleQuery query);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<RoleEntity> selectAllActiveRoles();

    /**
     * 根据角色ID，查找关联的权限ID
     *
     * @param roleId
     * @return
     */
    List<Integer> selectAssignedPermissionIdsByRoleId(@Param("roleId") int roleId);

    /**
     * 删除指定角色的所有角色与权限的关联关系
     *
     * @param roleId
     * @return
     */
    void deleteAllRolePermissionMappingsByRoleId(@Param("roleId") int roleId);

    /**
     * 添加角色与权限映射关系
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    int insertRolePermissionMappings(@Param("roleId") int roleId, @Param("permissionIds") List<Integer> permissionIds);
}
