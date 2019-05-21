package top.zuche.manager.console.system.controller;

import org.springframework.web.bind.annotation.*;
import top.zuche.manager.console.system.dto.RoleAssignPermissionsDTO;
import top.zuche.manager.console.system.dto.UserAssignRolesDTO;
import top.zuche.services.api.dto.RoleDTO;
import top.zuche.services.api.dto.Paging;
import top.zuche.services.api.query.RoleQuery;
import top.zuche.common.web.resp.ResponseData;
import top.zuche.services.api.service.RoleRpcService;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/system/role")
public class RoleController {

    // retries 设置为-1，则不重试
    @Reference(retries = -1)
    private RoleRpcService roleRpcService;

    // 分页查询
    @PostMapping("/queryPage")
    public ResponseData<Paging<RoleDTO>> queryPage(@RequestBody RoleQuery query) {
        Paging<RoleDTO> pageResult = roleRpcService.queryPageByCondition(query);
        return ResponseData.ok(pageResult);
    }

    // 根据主键查询指定对象
    @GetMapping("/queryOne")
    public ResponseData<RoleDTO> queryOne(int id) {
        RoleDTO dto = roleRpcService.queryRoleByPrimaryKey(id);
        return ResponseData.ok(dto);
    }

    // 增加和更新记录
    @PostMapping("/save")
    public ResponseData save(@RequestBody RoleDTO dto) {
        if (dto.getId() != null) {
            roleRpcService.updateRoleByPrimaryKey(dto);
        } else {
            roleRpcService.addRole(dto);
        }
        return ResponseData.ok();
    }

    // 删除记录
    @GetMapping("/del")
    public ResponseData del(int id) {
        roleRpcService.deleteRoleByPrimaryKey(id);
        return ResponseData.ok();
    }

    // 查询用户分配的角色ID
    @PostMapping("/queryAll")
    public ResponseData<List<RoleDTO>> queryAllActiveRoles() {
        List<RoleDTO> roles = roleRpcService.queryAllActiveRoles();
        return ResponseData.ok(roles);
    }

    // 查询用户分配的角色ID
    @GetMapping("/queryAssignedPermissionIds")
    public ResponseData<List<Integer>> queryPage(@RequestParam int roleId) {
        List<Integer> permissionIds = roleRpcService.queryAssignedPermissionIdsByRoleId(roleId);
        return ResponseData.ok(permissionIds);
    }

    @PostMapping("/assignPermissions")
    public ResponseData assignRoles(@RequestBody RoleAssignPermissionsDTO rap) {
        if (rap.getRoleId() == null) {
            return ResponseData.error("请指定要授权的角色");
        }
        roleRpcService.assignPermissionsForRole(rap.getRoleId(), rap.getPermissionIds());
        return ResponseData.ok();
    }
}
