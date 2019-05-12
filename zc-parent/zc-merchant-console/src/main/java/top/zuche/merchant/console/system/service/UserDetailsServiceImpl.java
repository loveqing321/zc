package top.zuche.merchant.console.system.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.zuche.merchant.console.system.dto.UserDetailsImpl;
import top.zuche.services.api.dto.UserDTO;
import top.zuche.services.api.exception.ServiceException;
import top.zuche.services.api.service.UserRpcService;

/**
 * Spring Security UserDetailsService实现
 * @author lzx
 * @date 2019/5/11 上午9:21
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(interfaceClass = UserRpcService.class)
    private UserRpcService userRpcService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDTO userDTO = userRpcService.queryUserByUsername(username);
            if (userDTO == null) {
                throw new UsernameNotFoundException(null);
            }
            UserDetailsImpl userDetails = new UserDetailsImpl();
            userDetails.setUsername(userDTO.getUsername());
            userDetails.setPassword(userDTO.getPassword());
            userDetails.setAccountNonExpired(!boolValue(userDTO.getAccountExpired()));
            userDetails.setAccountNonLocked(!boolValue(userDTO.getAccountLocked()));
            userDetails.setCredentialsNonExpired(!boolValue(userDTO.getCredentialsExpired()));
            userDetails.setUserId(userDTO.getId());
            userDetails.setMerchantId(userDTO.getMerchantId());
            userDetails.setEnabled(!boolValue(userDTO.getIsDeleted()));
            userDetails.setTelephone(userDTO.getTelephone());
            userDetails.setPost(userDTO.getPost());
            return userDetails;
        } catch (ServiceException e) {
            throw new UsernameNotFoundException(null);
        }
    }

    /**
     * int转布尔值
     *
     * @param i
     * @return
     */
    private boolean boolValue(Integer i) {
        if (i == null) return false;
        return i == 1;
    }
}
