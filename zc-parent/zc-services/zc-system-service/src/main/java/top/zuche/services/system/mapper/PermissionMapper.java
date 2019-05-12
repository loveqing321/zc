package top.zuche.services.system.mapper;

import org.apache.ibatis.annotations.Param;
import top.zuche.services.system.entity.PermissionEntity;

import java.util.List;

/**
 * 许可Mapper
 *
 * @author lzx
 * @date 2019/5/11 上午11:02
 */
public interface PermissionMapper {

    /**
     * 插入信息
     *
     * @param entity
     * @return
     */
    int insertPermission(PermissionEntity entity);

    /**
     * 批量插入
     *
     * @param users
     * @return
     */
    int batchInsertPermission(List<PermissionEntity> users);

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
     * @param name
     * @return
     */
    int deleteByName(String name);

    /**
     * 按照主键更新
     *
     * @param entity
     * @return
     */
    int updatePermissionByPrimaryKey(PermissionEntity entity);

    /**
     * 按照许可名更新
     *
     * @param entity
     * @return
     */
    int updatePermissionByName(PermissionEntity entity);

    /**
     * 根据许可名字查找信息
     *
     * @param name
     * @return
     */
    PermissionEntity selectPermissionByName(@Param("name") String name);

    /**
     * 根据角色名查找该角色的所有许可
     *
     * @param roleName
     * @return
     */
    List<PermissionEntity> selectPermissionsByRoleName(@Param("roleName") String roleName);

    /**
     * 根据用户名查找该用户的所有许可
     *
     * @param username
     * @return
     */
    List<PermissionEntity> selectPermissionsByUsername(@Param("username") String username);

}
