package top.zuche.manager.console.system.controller;

import top.zuche.services.api.dto.RoleDTO;
import top.zuche.services.api.dto.Paging;
import top.zuche.services.api.query.RoleQuery;
import top.zuche.common.web.resp.ResponseData;
import top.zuche.services.api.service.RoleRpcService;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/api/v1/system/role")
public class RoleController {

    @Reference
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
}
