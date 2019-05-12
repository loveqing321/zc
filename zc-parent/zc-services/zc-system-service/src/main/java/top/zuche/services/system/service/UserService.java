package top.zuche.services.system.service;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.zuche.common.base.BaseService;
import top.zuche.services.api.dto.UserDTO;
import top.zuche.services.api.exception.ServiceException;
import top.zuche.services.api.service.UserRpcService;
import top.zuche.services.system.Constants;
import top.zuche.services.system.entity.UserEntity;
import top.zuche.services.system.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务
 *
 * @author lzx
 * @date 2019/5/9 下午9:16
 */
@Service(interfaceClass = UserRpcService.class)
@Component("userService")
@Slf4j
public class UserService extends BaseService<UserEntity, UserDTO> implements UserRpcService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public void addUser(UserDTO user) {
        if (!StringUtils.hasText(user.getUsername())) {
            throw new ServiceException(Constants.ServiceMessage.EMPTY_USER_NAME);
        }
        int count = userMapper.insertUser(dto2Entity(user));
        if (count == 0) {
            throw new ServiceException(Constants.ServiceMessage.EXISTS_USER_NAME);
        }
    }

    @Override
    @Transactional
    public void batchAddUser(List<UserDTO> users) {
        if (users == null || users.isEmpty()) return;
        int expected = users.size();
        List<UserEntity> entities = new ArrayList<>(expected);
        users.forEach(dto -> { entities.add(dto2Entity(dto)); });
        int actual = userMapper.batchInsertUser(entities);
        if (expected != actual) {
            throw new ServiceException(Constants.ServiceMessage.EXISTS_USER_NAME);
        }
    }

    @Override
    @Transactional
    public int updateUserByPrimaryKey(UserDTO user) {
        if (user.getId() == null) {
            throw new ServiceException(Constants.ServiceMessage.LOSE_USE_ID);
        }
        int len = userMapper.updateUserByPrimaryKey(dto2Entity(user));
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UPDATE_USER_FAIL);
        }
        return len;
    }

    @Override
    @Transactional
    public int updateUserByUsername(UserDTO user) {
        if (user.getUsername() == null) {
            throw new ServiceException(Constants.ServiceMessage.LOSE_USE_NAME);
        }
        int len = userMapper.updateUserByUsername(dto2Entity(user));
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UPDATE_USER_FAIL);
        }
        return len;
    }

    @Override
    @Transactional
    public int deleteByPrimaryKey(int id) {
        int len = userMapper.deleteByPrimaryKey(id);
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UN_EXISTS_USER_ID);
        }
        return len;
    }

    @Override
    public int deleteByUsername(String username) {
        int len = userMapper.deleteByUsername(username);
        if (len != 1) {
            throw new ServiceException(Constants.ServiceMessage.UN_EXISTS_USER_NAME);
        }
        return len;
    }

    /**
     * 使用用户名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public UserDTO queryUserByUsername(String username) {
        UserEntity entity = userMapper.selectUserByUsername(username);
        return entity == null ? null : entity2Dto(entity);
    }

    /**
     * 查询用户信息，携带角色列表
     *
     * @param username
     * @return
     */
    @Override
    public UserDTO queryUserWithRolesByUsername(String username) {
        UserEntity entity = userMapper.selectUserWithRolesByUsername(username);
        return entity == null ? null : entity2Dto(entity);
    }
}
