package top.zuche.services.api.service;

import top.zuche.services.api.dto.DictDTO;
import top.zuche.services.api.dto.Paging;
import top.zuche.services.api.exception.ServiceException;
import top.zuche.services.api.query.DictQuery;

import java.util.List;

/**
* Dict服务接口
*
* @author lzx
* @date 2018/12/16 5:11 PM
*/
public interface DictRpcService {

    // 添加实体
    void addDict(DictDTO dto) throws ServiceException;

    // 根据主键按需更新
    void updateDictByPrimaryKey(DictDTO dto) throws ServiceException;

    // 根据主键删除
    void deleteDictByPrimaryKey(int id) throws ServiceException;

    // 根据主键查询单个实体
    DictDTO queryDictByPrimaryKey(int id) throws ServiceException;

    // 根据查询条件，查询所有满足的实体
    List<DictDTO> queryAllDictsByCondition(DictQuery query) throws ServiceException;

    // 根据查询条件，执行分页查询
    Paging<DictDTO> queryPageByCondition(DictQuery query) throws ServiceException;

}