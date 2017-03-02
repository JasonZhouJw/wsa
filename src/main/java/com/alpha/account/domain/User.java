package com.alpha.account.domain;

import com.alpha.account.entities.Permission;
import com.alpha.account.exception.UserException;
import com.alpha.account.exception.UserExistException;
import com.alpha.account.exception.UserPasswordException;
import com.alpha.account.model.UserVo;
import com.alpha.account.repository.PermissionRepository;
import com.alpha.account.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jzhou237 on 2016-12-06.
 */
@Component
public class User implements IUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public com.alpha.account.entities.User login(String name, String password) {
        return this.userRepository.login(name, password);
    }

    @Transactional
    public com.alpha.account.entities.User create(UserVo userVo) throws UserException {
        if (!StringUtils.equals(userVo.getPassword(), userVo.getRepeatPassword())) {
            throw new UserPasswordException("Password is not same.");
        }
        com.alpha.account.entities.User existUser = this.userRepository.findByName(userVo.getName());
        if (existUser != null) {
            throw new UserExistException("User Name [" + userVo.getName() + "] is existing.");
        }
        com.alpha.account.entities.User user = new com.alpha.account.entities.User();
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
        user.setName(userVo.getName());
        user.setPassword(bc.encode(userVo.getPassword()));
        if (userVo.isAdmin()) {
            List<Permission> permissionList = permissionRepository.findAll();
            user.setPermissions(permissionList);
        }
        return this.userRepository.save(user);
    }
}
