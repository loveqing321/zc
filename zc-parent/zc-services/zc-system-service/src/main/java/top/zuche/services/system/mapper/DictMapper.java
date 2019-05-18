package top.zuche.services.system.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import top.zuche.services.api.query.DictQuery;
import top.zuche.services.system.entity.DictEntity;

import java.util.List;

/**
 * Dict Mapper
 *
 * @author lzx
 * @date 2019/5/16 上午5:12
 */
public interface DictMapper {

    /**
     * 插入信息
     *
     * @param entity
     * @return
     */
    int insertDict(DictEntity entity);

    /**
     * 按照主键更新
     *
     * @param entity
     * @return
     */
    int updateDictByPrimaryKey(DictEntity entity);

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteDictByPrimaryKey(int id);

    /**
     * 根据角色ID查找信息
     *
     * @param id
     * @return
     */
    DictEntity selectDictByPrimaryKey(@Param("id") int id);

    /**
     * 根据查询条件查找信息
     *
     * @param query
     * @return
     */
    List<DictEntity> selectDictsByCondition(DictQuery query);

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    Page<DictEntity> selectPageByCondition(DictQuery query);
}
