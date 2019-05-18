package top.zuche.services.system.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.zuche.common.base.BaseService;
import top.zuche.services.api.dto.Paging;
import top.zuche.services.api.dto.PermissionDTO;
import top.zuche.services.api.exception.ServiceException;
import top.zuche.services.api.query.PermissionQuery;
import top.zuche.services.api.service.PermissionRpcService;
import top.zuche.services.system.Constants;
import top.zuche.services.system.entity.PermissionEntity;
import top.zuche.services.system.mapper.PermissionMapper;
import top.zuche.toolkit.cache.CacheNames;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lzx
 * @date 2019/5/12 下午4:09
 */
@Service(interfaceClass = PermissionRpcService.class)
@Component("permissionService")
@Slf4j
@CacheConfig(cacheNames = CacheNames.SYSTEM_PERMISSION_CACHE)
public class PermissionService extends BaseService<PermissionEntity, PermissionDTO> implements PermissionRpcService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void addPermission(PermissionDTO permission) throws ServiceException {
        if (!StringUtils.hasText(permission.getName())) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_PERMISSION_NAME);
        }
        PermissionEntity entity = dto2Entity(permission);
        int count = permissionMapper.insertPermission(entity);
        if (count == 0) {
            throw new ServiceException(Constants.ServiceMessage.EXISTS_PERMISSION_NAME);
        }
        permission.setId(entity.getId());
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void batchAddPermission(List<PermissionDTO> permissions) throws ServiceException {
        if (permissions == null || permissions.isEmpty()) return;
        int expected = permissions.size();
        List<PermissionEntity> entities = new ArrayList<>(expected);
        permissions.forEach(dto -> { entities.add(dto2Entity(dto)); });
        int actual = permissionMapper.batchInsertPermission(entities);
        if (expected != actual) {
            throw new ServiceException(Constants.ServiceMessage.EXISTS_PERMISSION_NAME);
        }
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void updatePermissionByPrimaryKey(PermissionDTO permission) throws ServiceException {
        if (permission.getId() == null) {
            throw new ServiceException(Constants.ServiceMessage.LOSE_PERMISSION_ID);
        }
        int len = permissionMapper.updatePermissionByPrimaryKey(dto2Entity(permission));
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UPDATE_PERMISSION_FAIL);
        }
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void updatePermissionByName(PermissionDTO permission) throws ServiceException {
        if (permission.getName() == null) {
            throw new ServiceException(Constants.ServiceMessage.LOSE_PERMISSION_NAME);
        }
        int len = permissionMapper.updatePermissionByName(dto2Entity(permission));
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UPDATE_PERMISSION_FAIL);
        }
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void deletePermissionByPrimaryKey(int id) throws ServiceException {
        int len = permissionMapper.deletePermissionByPrimaryKey(id);
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UN_EXISTS_PERMISSION_ID);
        }
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void deletePermissionByName(String name) throws ServiceException {
        if (!StringUtils.hasText(name)) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_PERMISSION_NAME);
        }
        int len = permissionMapper.deletePermissionByName(name);
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UN_EXISTS_PERMISSION_NAME);
        }
    }

    @Override
    public PermissionDTO queryPermissionByPrimaryKey(int id) throws ServiceException {
        PermissionEntity entity = permissionMapper.selectPermissionByPrimaryKey(id);
        return entity == null ? null : entity2Dto(entity);
    }

    @Override
    public PermissionDTO queryPermissionByName(String name) throws ServiceException {
        if (!StringUtils.hasText(name)) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_PERMISSION_NAME);
        }
        PermissionEntity entity = permissionMapper.selectPermissionByName(name);
        return entity == null ? null : entity2Dto(entity);
    }

    @Override
    public List<PermissionDTO> queryPermissionsByRoleName(String roleName) throws ServiceException {
        if (!StringUtils.hasText(roleName)) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_ROLE_NAME);
        }
        List<PermissionEntity> entities = permissionMapper.selectPermissionsByRoleName(roleName);
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        return entities.stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    @Cacheable(key = "#root.targetClass + '.' + #root.methodName + '.' + #p0")
    public List<PermissionDTO> queryPermissionsByUsername(String username) throws ServiceException {
        if (!StringUtils.hasText(username)) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_USER_NAME);
        }
        List<PermissionEntity> entities = permissionMapper.selectPermissionsByUsername(username);
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        return entities.stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public Paging<PermissionDTO> queryPageByCondition(PermissionQuery query) throws ServiceException {
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        Page<PermissionEntity> page = permissionMapper.selectPageByCondition(query);
        if (page.getTotal() == 0) {
            return Paging.of(0, new ArrayList<>(0));
        }
        List<PermissionEntity> result = page.getResult();
        List<PermissionDTO> dtos = result.stream().map(this::entity2Dto).collect(Collectors.toList());
        return Paging.of(page.getTotal(), dtos);
    }
}
