package top.zuche.services.api.service;

import top.zuche.services.api.dto.RoleDTO;
import top.zuche.services.api.exception.ServiceException;

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
    void deleteByPrimaryKey(int id) throws ServiceException;

    /**
     * 根据角色名删除
     *
     * @param roleName
     * @return
     */
    void deleteByRoleName(String roleName) throws ServiceException;

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

}