package com.alpha.account.domain;

import com.alpha.account.entities.User;
import com.alpha.account.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by jzhou237 on 2017-02-21.
 */
@Component
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUserDetail securityUserDetail = null;
        if (StringUtils.isNotEmpty(username)) {
            User user = this.userRepository.findByName(username);
            if (user != null) {
                securityUserDetail = new SecurityUserDetail(user);
            }
        }
        if (securityUserDetail == null) {
            throw new UsernameNotFoundException("Can not find the User named [" + username + "]");
        }
        return securityUserDetail;
    }
}
