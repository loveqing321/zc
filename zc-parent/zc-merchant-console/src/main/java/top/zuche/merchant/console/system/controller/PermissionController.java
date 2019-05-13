package top.zuche.merchant.console.system.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.zuche.common.web.resp.ResponseData;
import top.zuche.merchant.console.system.dto.UserDetailsImpl;
import top.zuche.merchant.console.utils.UserUtil;
import top.zuche.services.api.dto.PermissionDTO;
import top.zuche.services.api.service.PermissionRpcService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户控制器
 *
 * @author lzx
 * @date 2019/5/9 下午2:52
 */
@Slf4j
@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Reference
    private PermissionRpcService permissionRpcService;

    @RequestMapping(value = "/getUserPermissions", method = RequestMethod.POST)
    public ResponseData<List<String>> getUserPermissions() {
        UserDetailsImpl user = UserUtil.getCurrentUser();
        List<PermissionDTO> permissions = permissionRpcService.queryPermissionsByUsername(user.getUsername());
        if (permissions == null || permissions.isEmpty()) {
            return ResponseData.ok(new ArrayList<>());
        }
        List<String> perms = permissions.stream().map(PermissionDTO::getPerm).collect(Collectors.toList());
        return ResponseData.ok(perms);
    }
}
