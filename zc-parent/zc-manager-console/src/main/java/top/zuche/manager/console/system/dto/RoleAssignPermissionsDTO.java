package top.zuche.manager.console.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lzx
 * @date 2019/5/20 下午8:41
 */
@Data
public class RoleAssignPermissionsDTO implements Serializable {

    private Integer roleId;

    private List<Integer> permissionIds;
}
