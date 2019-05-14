package top.zuche.services.api.service;

import top.zuche.services.api.dto.Paging;
import top.zuche.services.api.dto.UserDTO;
import top.zuche.services.api.exception.ServiceException;
import top.zuche.services.api.query.UserQuery;

import java.util.List;

/**
 * 用户服务
 *
 * @author lzx
 * @date 2019/5/9 下午8:19
 */
public interface UserRpcService {

    /**
     * 通过用户名查找用户
     *
     * @param username
     * @return
     */
    UserDTO queryUserByUsername(String username) throws ServiceException;

    /**
     * 通过用户名查找用户，携带角色列表
     *
     * @param username
     * @return
     */
    UserDTO queryUserWithRolesByUsername(String username) throws ServiceException;

    /**
     * 通过用户名查找用户，携带许可列表
     *
     * @param username
     * @return
     */
    UserDTO queryUserWithPermissionsByUsername(String username) throws ServiceException;

    /**
     * 根据查询条件，执行分页查询
     *
     * @param query
     * @return
     * @throws ServiceException
     */
    Paging<UserDTO> queryPageByCondition(UserQuery query) throws ServiceException;

    /**
     * 添加用户
     *
     * @param user
     */
    void addUser(UserDTO user) throws ServiceException;

    /**
     * 批量添加用户
     *
     * @param users
     */
    void batchAddUser(List<UserDTO> users) throws ServiceException;

    /**
     * 按照主键更新
     *
     * @param user
     * @return
     */
    void updateUserByPrimaryKey(UserDTO user) throws ServiceException;

    /**
     * 按照username更新
     *
     * @param user
     * @return
     */
    void updateUserByUsername(UserDTO user) throws ServiceException;

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    void deleteByPrimaryKey(int id) throws ServiceException;

    /**
     * 根据username删除
     *
     * @param username
     * @return
     */
    void deleteByUsername(String username) throws ServiceException;

}
