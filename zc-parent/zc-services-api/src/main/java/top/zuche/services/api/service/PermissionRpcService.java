package top.zuche.services.api.service;

import top.zuche.services.api.dto.Paging;
import top.zuche.services.api.dto.PermissionDTO;
import top.zuche.services.api.exception.ServiceException;
import top.zuche.services.api.query.PermissionQuery;

import java.util.List;

/**
 * 许可服务
 *
 * @author lzx
 * @date 2019/5/9 下午8:19
 */
public interface PermissionRpcService {

    /**
     * 添加角色
     *
     * @param permission
     */
    void addPermission(PermissionDTO permission) throws ServiceException;

    /**
     * 批量添加角色
     *
     * @param permissions
     */
    void batchAddPermission(List<PermissionDTO> permissions) throws ServiceException;

    /**
     * 按照主键更新
     *
     * @param permission
     * @return
     */
    void updatePermissionByPrimaryKey(PermissionDTO permission) throws ServiceException;

    /**
     * 按照许可名更新
     *
     * @param permission
     * @return
     */
    void updatePermissionByName(PermissionDTO permission) throws ServiceException;

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    void deletePermissionByPrimaryKey(int id) throws ServiceException;

    /**
     * 根据许可名删除
     *
     * @param name
     * @return
     */
    void deletePermissionByName(String name) throws ServiceException;

    /**
     * 通过许可ID查找许可
     *
     * @param id
     * @return
     */
    PermissionDTO queryPermissionByPrimaryKey(int id) throws ServiceException;

    /**
     * 通过许可名查找许可
     *
     * @param name
     * @return
     */
    PermissionDTO queryPermissionByName(String name) throws ServiceException;

    /**
     * 通过角色名查找许可列表
     *
     * @param roleName
     * @return
     */
    List<PermissionDTO> queryPermissionsByRoleName(String roleName) throws ServiceException;

    /**
     * 通过用户账号查找许可列表
     *
     * @param username
     * @return
     * @throws ServiceException
     */
    List<PermissionDTO> queryPermissionsByUsername(String username) throws ServiceException;

    /**
     * 通过条件分页查找许可列表
     *
     * @param query
     * @return
     * @throws ServiceException
     */
    Paging<PermissionDTO> queryPageByCondition(PermissionQuery query) throws ServiceException;

}
