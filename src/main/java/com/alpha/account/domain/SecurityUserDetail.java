package com.alpha.account.domain;

import com.alpha.account.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jzhou237 on 2017-02-21.
 */
public class SecurityUserDetail extends User implements UserDetails {

    SecurityUserDetail(User user) {
        this.setId(user.getId());
        this.setName(user.getName());
        this.setPassword(user.getPassword());
//        this.setPhone(user.getPhone());
        this.setPermissions(user.getPermissions());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList();
        this.getPermissions().forEach(permission -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(permission.getName());
            authorityList.add(authority);
        });
        return authorityList;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
