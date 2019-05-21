package top.zuche.services.system.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.zuche.common.base.BaseService;
import top.zuche.services.api.dto.DictDTO;
import top.zuche.services.api.dto.Paging;
import top.zuche.services.api.exception.ServiceException;
import top.zuche.services.api.query.DictQuery;
import top.zuche.services.api.service.DictRpcService;
import top.zuche.services.system.Constants;
import top.zuche.services.system.entity.DictEntity;
import top.zuche.services.system.mapper.DictMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典服务
 *
 * @author lzx
 * @date 2019/5/16 上午5:08
 */
@Service(interfaceClass = DictRpcService.class)
@Component("dictService")
public class DictService extends BaseService<DictEntity, DictDTO> implements DictRpcService {

    @Resource
    private DictMapper dictMapper;

    @Override
    @Transactional
    public void addDict(DictDTO dto) throws ServiceException {
        if (!StringUtils.hasText(dto.getDictType())) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_DICT_TYPE);
        }
        if (!StringUtils.hasText(dto.getDictCode())) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_DICT_CODE);
        }
        if (!StringUtils.hasText(dto.getDictValue())) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_DICT_CODE);
        }
        DictEntity entity = dto2Entity(dto);
        int count = dictMapper.insertDict(entity);
        if (count == 0) {
            throw new ServiceException(Constants.ServiceMessage.EXISTS_DICT_TYPE_CODE);
        }
        dto.setId(entity.getId());
    }

    @Override
    @Transactional
    public void updateDictByPrimaryKey(DictDTO dto) throws ServiceException {
        if (dto.getId() == null) {
            throw new ServiceException(Constants.ServiceMessage.LOSE_DICT_ID);
        }
        int len = dictMapper.updateDictByPrimaryKey(dto2Entity(dto));
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UPDATE_DICT_FAIL);
        }
    }

    @Override
    @Transactional
    public void deleteDictByPrimaryKey(int id) throws ServiceException {
        int len = dictMapper.deleteDictByPrimaryKey(id);
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UN_EXISTS_DICT_ID);
        }
    }

    @Override
    public DictDTO queryDictByPrimaryKey(int id) throws ServiceException {
        DictEntity entity = dictMapper.selectDictByPrimaryKey(id);
        return entity == null ? null : entity2Dto(entity);
    }

    @Override
    public List<DictDTO> queryAllDictsByCondition(DictQuery query) throws ServiceException {
        List<DictEntity> entities = dictMapper.selectDictsByCondition(query);
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        return entities.stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public Paging<DictDTO> queryPageByCondition(DictQuery query) throws ServiceException {
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        Page<DictEntity> page = dictMapper.selectPageByCondition(query);
        if (page.getTotal() == 0) {
            return Paging.of(0, new ArrayList<>(0));
        }
        List<DictEntity> result = page.getResult();
        List<DictDTO> dtos = result.stream().map(this::entity2Dto).collect(Collectors.toList());
        return Paging.of(page.getTotal(), dtos);
    }
}
