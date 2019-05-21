package top.zuche.manager.console.system.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.zuche.common.web.resp.ResponseData;
import top.zuche.services.api.dto.Paging;
import top.zuche.services.api.dto.PermissionDTO;
import top.zuche.services.api.query.PermissionQuery;
import top.zuche.services.api.service.PermissionRpcService;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/system/permission")
public class PermissionController {

    @Reference
    private PermissionRpcService permissionRpcService;

    // 获取默认权限
    @PostMapping(value = "/getUserPermissions")
    public ResponseData<List<String>> getUserPermissions() {
        // 所有权限
        return ResponseData.ok(Arrays.asList("*"));
    }

    // 分页查询
    @PostMapping("/queryPage")
    public ResponseData<Paging<PermissionDTO>> queryPage(@RequestBody PermissionQuery query) {
        Paging<PermissionDTO> pageResult = permissionRpcService.queryPageByCondition(query);
        return ResponseData.ok(pageResult);
    }

    // 根据主键查询指定对象
    @GetMapping("/queryOne")
    public ResponseData<PermissionDTO> queryOne(int id) {
        PermissionDTO dto = permissionRpcService.queryPermissionByPrimaryKey(id);
        return ResponseData.ok(dto);
    }

    // 增加和更新记录
    @PostMapping("/save")
    public ResponseData save(@RequestBody PermissionDTO dto) {
        if (dto.getId() != null) {
            permissionRpcService.updatePermissionByPrimaryKey(dto);
        } else {
            permissionRpcService.addPermission(dto);
        }
        return ResponseData.ok();
    }

    // 删除记录
    @GetMapping("/del")
    public ResponseData del(int id) {
        permissionRpcService.deletePermissionByPrimaryKey(id);
        return ResponseData.ok();
    }

    // 查询用户分配的角色ID
    @PostMapping("/queryAll")
    public ResponseData<List<PermissionDTO>> queryAllActivePermissions() {
        List<PermissionDTO> roles = permissionRpcService.queryAllActivePermissions();
        return ResponseData.ok(roles);
    }
}
