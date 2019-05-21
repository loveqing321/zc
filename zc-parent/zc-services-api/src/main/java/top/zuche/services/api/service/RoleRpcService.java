package top.zuche.services.api.service;

import top.zuche.services.api.dto.Paging;
import top.zuche.services.api.dto.RoleDTO;
import top.zuche.services.api.exception.ServiceException;
import top.zuche.services.api.query.RoleQuery;

import java.util.List;

/**
 * 角色服务
 *
 * @author lzx
 * @date 2019/5/9 下午8:19
 */
public interface RoleRpcService {

    /**
     * 添加角色
     *
     * @param role
     */
    void addRole(RoleDTO role) throws ServiceException;

    /**
     * 批量添加角色
     *
     * @param roles
     */
    void batchAddRole(List<RoleDTO> roles) throws ServiceException;

    /**
     * 按照主键更新
     *
     * @param role
     * @return
     */
    void updateRoleByPrimaryKey(RoleDTO role) throws ServiceException;

    /**
     * 按照角色名更新
     *
     * @param role
     * @return
     */
    void updateRoleByRoleName(RoleDTO role) throws ServiceException;

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    void deleteRoleByPrimaryKey(int id) throws ServiceException;

    /**
     * 根据角色名删除
     *
     * @param roleName
     * @return
     */
    void deleteRoleByRoleName(String roleName) throws ServiceException;

    /**
     * 通过ID查找角色
     *
     * @param id
     * @return
     */
    RoleDTO queryRoleByPrimaryKey(int id) throws ServiceException;

    /**
     * 通过角色名查找角色
     *
     * @param ruleName
     * @return
     */
    RoleDTO queryRoleByRoleName(String ruleName) throws ServiceException;

    /**
     * 通过角色名查找角色，携带许可列表
     *
     * @param roleName
     * @return
     */
    RoleDTO queryRoleWithPermissionsByRoleName(String roleName) throws ServiceException;

    /**
     * 通过用户账号查找角色列表
     *
     * @param username
     * @return
     * @throws ServiceException
     */
    List<RoleDTO> queryRolesByUsername(String username) throws ServiceException;

    /**
     * 通过条件查找角色列表
     *
     * @param query
     * @return
     * @throws ServiceException
     */
    Paging<RoleDTO> queryPageByCondition(RoleQuery query) throws ServiceException;

    /**
     * 查询所有角色
     *
     * @return
     * @throws ServiceException
     */
    List<RoleDTO> queryAllActiveRoles() throws ServiceException;

    /**
     * 查询角色分配的权限ID
     *
     * @param roleId
     * @return
     * @throws ServiceException
     */
    List<Integer> queryAssignedPermissionIdsByRoleId(int roleId) throws ServiceException;

    /**
     * 为角色分配指定权限
     *
     * @param roleId
     * @param permissionIds
     * @throws ServiceException
     */
    void assignPermissionsForRole(int roleId, List<Integer> permissionIds) throws ServiceException;
}
