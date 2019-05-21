package top.zuche.manager.console.system.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.zuche.common.web.resp.ResponseData;
import top.zuche.manager.console.system.dto.UserAssignRolesDTO;
import top.zuche.services.api.dto.Paging;
import top.zuche.services.api.dto.UserDTO;
import top.zuche.services.api.query.UserQuery;
import top.zuche.services.api.service.UserRpcService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/system/user")
public class UserController {

    @Reference(retries = -1)
    private UserRpcService userRpcService;

    // 分页查询
    @PostMapping("/queryPage")
    public ResponseData<Paging<UserDTO>> queryPage(@RequestBody UserQuery query) {
        Paging<UserDTO> pageResult = userRpcService.queryPageByCondition(query);
        return ResponseData.ok(pageResult);
    }

    // 根据主键查询指定对象
    @GetMapping("/queryOne")
    public ResponseData<UserDTO> queryOne(String username) {
        UserDTO dto = userRpcService.queryUserByUsername(username);
        dto.setPassword(null);
        return ResponseData.ok(dto);
    }

    // 增加和更新记录
    @PostMapping("/save")
    public ResponseData save(@RequestBody UserDTO dto) {
        if (dto.getId() != null) {
            userRpcService.updateUserByPrimaryKey(dto);
        } else {
            userRpcService.addUser(dto);
        }
        return ResponseData.ok();
    }

    // 删除记录
    @GetMapping("/del")
    public ResponseData del(int id) {
        userRpcService.deleteByPrimaryKey(id);
        return ResponseData.ok();
    }

    // 查询用户分配的角色ID
    @GetMapping("/queryAssignedRoleIds")
    public ResponseData<List<Integer>> queryPage(@RequestParam int userId) {
        List<Integer> roleIds = userRpcService.queryAssignedRoleIdsByUserId(userId);
        return ResponseData.ok(roleIds);
    }

    @PostMapping("/assignRoles")
    public ResponseData assignRoles(@RequestBody UserAssignRolesDTO uar) {
        if (uar.getUserId() == null) {
            return ResponseData.error("请指定要授权的用户");
        }
        if (uar.getRoleIds() == null) {
            uar.setRoleIds(new ArrayList<>(0));
        }
        userRpcService.assignRolesForUser(uar.getUserId(), uar.getRoleIds());
        return ResponseData.ok();
    }
}
