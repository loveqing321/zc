package top.zuche.services.system.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.zuche.common.base.BaseService;
import top.zuche.services.api.dto.Paging;
import top.zuche.services.api.dto.RoleDTO;
import top.zuche.services.api.exception.ServiceException;
import top.zuche.services.api.query.RoleQuery;
import top.zuche.services.api.service.RoleRpcService;
import top.zuche.services.system.Constants;
import top.zuche.services.system.entity.RoleEntity;
import top.zuche.services.system.mapper.RoleMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lzx
 * @date 2019/5/12 下午3:26
 */
@Slf4j
@Component("roleService")
@Service(interfaceClass = RoleRpcService.class)
public class RoleService extends BaseService<RoleEntity, RoleDTO> implements RoleRpcService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    @Transactional
    public void addRole(RoleDTO role) throws ServiceException {
        if (!StringUtils.hasText(role.getRoleName())) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_ROLE_NAME);
        }
        RoleEntity entity = dto2Entity(role);
        int count = roleMapper.insertRole(entity);
        if (count == 0) {
            throw new ServiceException(Constants.ServiceMessage.EXISTS_ROLE_NAME);
        }
        role.setId(entity.getId());
    }

    @Override
    @Transactional
    public void batchAddRole(List<RoleDTO> roles) throws ServiceException {
        if (roles == null || roles.isEmpty()) return;
        int expected = roles.size();
        List<RoleEntity> entities = new ArrayList<>(expected);
        roles.forEach(dto -> { entities.add(dto2Entity(dto)); });
        int actual = roleMapper.batchInsertRole(entities);
        if (expected != actual) {
            throw new ServiceException(Constants.ServiceMessage.EXISTS_ROLE_NAME);
        }
    }

    @Override
    @Transactional
    public void updateRoleByPrimaryKey(RoleDTO role) throws ServiceException {
        if (role.getId() == null) {
            throw new ServiceException(Constants.ServiceMessage.LOSE_ROLE_ID);
        }
        int len = roleMapper.updateRoleByPrimaryKey(dto2Entity(role));
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UPDATE_ROLE_FAIL);
        }
    }

    @Override
    @Transactional
    public void updateRoleByRoleName(RoleDTO role) throws ServiceException {
        if (role.getRoleName() == null) {
            throw new ServiceException(Constants.ServiceMessage.LOSE_ROLE_NAME);
        }
        int len = roleMapper.updateRoleByRoleName(dto2Entity(role));
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UPDATE_ROLE_FAIL);
        }
    }

    @Override
    @Transactional
    public void deleteRoleByPrimaryKey(int id) throws ServiceException {
        int len = roleMapper.deleteByPrimaryKey(id);
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UN_EXISTS_ROLE_ID);
        }
    }

    @Override
    @Transactional
    public void deleteRoleByRoleName(String roleName) throws ServiceException {
        if (!StringUtils.hasText(roleName)) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_ROLE_NAME);
        }
        int len = roleMapper.deleteByRoleName(roleName);
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UN_EXISTS_ROLE_NAME);
        }
    }

    @Override
    public RoleDTO queryRoleByPrimaryKey(int id) throws ServiceException {
        RoleEntity entity = roleMapper.selectByPrimaryKey(id);
        return entity == null ? null : entity2Dto(entity);
    }

    @Override
    public RoleDTO queryRoleByRoleName(String roleName) throws ServiceException {
        if (!StringUtils.hasText(roleName)) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_ROLE_NAME);
        }
        RoleEntity entity = roleMapper.selectRoleByRoleName(roleName);
        return entity == null ? null : entity2Dto(entity);
    }

    @Override
    public RoleDTO queryRoleWithPermissionsByRoleName(String roleName) throws ServiceException {
        if (!StringUtils.hasText(roleName)) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_ROLE_NAME);
        }
        RoleEntity entity = roleMapper.selectRoleWithPermissionsByRoleName(roleName);
        return entity == null ? null : entity2Dto(entity);
    }

    @Override
    public List<RoleDTO> queryRolesByUsername(String username) throws ServiceException {
        if (!StringUtils.hasText(username)) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_USER_NAME);
        }
        List<RoleEntity> entities = roleMapper.selectRolesByUsername(username);
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        return entities.stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public Paging<RoleDTO> queryPageByCondition(RoleQuery query) throws ServiceException {
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        Page<RoleEntity> page = roleMapper.selectPageByCondition(query);
        if (page.getTotal() == 0) {
            return Paging.of(0, new ArrayList<>(0));
        }
        List<RoleEntity> result = page.getResult();
        List<RoleDTO> dtos = result.stream().map(this::entity2Dto).collect(Collectors.toList());
        return Paging.of(page.getTotal(), dtos);
    }
}
