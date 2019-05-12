package top.zuche.services.api.service;

import top.zuche.services.api.dto.UserDTO;
import top.zuche.services.api.exception.ServiceException;

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
    int updateUserByPrimaryKey(UserDTO user) throws ServiceException;

    /**
     * 按照username更新
     *
     * @param user
     * @return
     */
    int updateUserByUsername(UserDTO user) throws ServiceException;

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id) throws ServiceException;

    /**
     * 根据username删除
     *
     * @param username
     * @return
     */
    int deleteByUsername(String username) throws ServiceException;

}
