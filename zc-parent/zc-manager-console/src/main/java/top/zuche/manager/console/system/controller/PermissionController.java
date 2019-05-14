package top.zuche.manager.console.system.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.zuche.common.web.resp.ResponseData;
import top.zuche.services.api.service.PermissionRpcService;

import java.util.Arrays;
import java.util.List;

/**
 * 许可控制器
 *
 * @author lzx
 * @date 2019/5/9 下午2:52
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/system/permission")
public class PermissionController {

    @Reference
    private PermissionRpcService permissionRpcService;

    @RequestMapping(value = "/getUserPermissions", method = RequestMethod.POST)
    public ResponseData<List<String>> getUserPermissions() {
        // 所有权限
        return ResponseData.ok(Arrays.asList("*"));
    }
}
