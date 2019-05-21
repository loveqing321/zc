package top.zuche.manager.console.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lzx
 * @date 2019/5/20 下午8:41
 */
@Data
public class UserAssignRolesDTO implements Serializable {

    private Integer userId;

    private List<Integer> roleIds;
}
