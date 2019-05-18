package top.zuche.services.system.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import top.zuche.services.api.dto.RoleDTO;
import top.zuche.services.api.exception.ServiceException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzx
 * @date 2019/5/11 下午12:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class RoleServiceTest {

    @Resource
    private RoleService roleService;

    @Test
    public void addRole() throws ServiceException {
        RoleDTO role = new RoleDTO();
        role.setRoleName("abc");
        roleService.addRole(role);
        System.out.println(role.getId());
    }

    @Test
    public void batchAddRole() throws ServiceException {
        List<RoleDTO> roles = new ArrayList<>();
        RoleDTO role1 = new RoleDTO();
        role1.setRoleName("kk");

        RoleDTO role2 = new RoleDTO();
        role2.setRoleName("mm");

        roles.add(role1);
        roles.add(role2);
        roleService.batchAddRole(roles);
    }

    @Test
    public void queryRoleByRoleName() throws ServiceException {
        RoleDTO role = roleService.queryRoleByRoleName("mm");
        Assert.assertNotNull(role);
    }

    @Test
    public void queryRolesByUsername() throws ServiceException {
        List<RoleDTO> roles = roleService.queryRolesByUsername("13333201150");
        Assert.assertNotNull(roles);
    }

    @Test
    public void queryRoleWithPermissionsByRoleName() throws ServiceException {
        RoleDTO role = roleService.queryRoleWithPermissionsByRoleName("mm");
        Assert.assertNotNull(role);
    }

    @Test
    public void updateRoleByPrimaryKey() throws ServiceException {
        RoleDTO role = new RoleDTO();
        role.setId(2);
        role.setRoleName("a");
        roleService.updateRoleByPrimaryKey(role);
    }

    @Test
    public void updateRoleByRoleName() throws ServiceException {
        RoleDTO role = new RoleDTO();
        role.setRoleName("a");
        role.setIsDeleted(1);
        roleService.updateRoleByRoleName(role);
    }

    @Test
    public void deleteByPrimaryKey() throws ServiceException {
        roleService.deleteRoleByPrimaryKey(1);
    }

    @Test
    public void deleteByRoleName() throws ServiceException {
        roleService.deleteRoleByRoleName("mm");
    }
}