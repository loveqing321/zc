package top.zuche.manager.console.system.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.zuche.manager.console.system.dto.UserDetailsImpl;

/**
 * Spring Security UserDetailsService实现
 * @author lzx
 * @date 2019/5/11 上午9:21
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDetails userDetails = new UserDetailsImpl();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!userDetails.getUsername().equals(username)) {
            throw new UsernameNotFoundException("for " + username);
        }
        return userDetails;
    }
}
