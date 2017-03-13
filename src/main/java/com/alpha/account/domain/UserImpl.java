package com.alpha.account.domain;

import com.alpha.account.entities.Permission;
import com.alpha.account.entities.User;
import com.alpha.account.exception.UserException;
import com.alpha.account.exception.UserExistException;
import com.alpha.account.exception.UserNotFoundException;
import com.alpha.account.exception.UserPasswordException;
import com.alpha.account.model.UserVo;
import com.alpha.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 2016-12-06.
 */
@Component
public class UserImpl implements IUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IPermissionDo permissionDo;

    public User login(String name, String password) {
        return this.userRepository.login(name, password);
    }

    public User create(UserVo userVo) throws UserException {
        User existUser = this.userRepository.findByName(userVo.getName());
        if (existUser != null) {
            throw new UserExistException("User Name [" + userVo.getName() + "] is existing.");
        }
        User user = new User();
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
        user.setName(userVo.getName());
        user.setPassword(bc.encode(userVo.getPassword()));
        if (userVo.isAdmin()) {
            List<Permission> permissionList = permissionDo.findAll();
            user.setPermissions(permissionList);
        }
        return this.userRepository.save(user);
    }

    @Override
    public void findAll(User user, Pageable pageable, Consumer<Page<User>> consumer) {
        Page<User> userPage = this.userRepository.findAll(Example.of(user), pageable);
        if (userPage != null) {
            consumer.accept(userPage);
        }
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User update(UserVo userVo) throws UserNotFoundException {
        User updatedUser = this.userRepository.findOne(userVo.getId());
        if (updatedUser == null) {
            throw new UserNotFoundException("User ID [" + userVo.getId() + "] is not found.");
        }
        updatedUser.setActive(userVo.isActive());
        return this.userRepository.save(updatedUser);
    }

    @Override
    public User changePassword(UserVo userVo) throws UserNotFoundException, UserPasswordException {
        User user = this.userRepository.findOne(userVo.getId());
        if (user == null) {
            throw new UserNotFoundException("User ID [" + userVo.getId() + "] is not found.");
        }
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
        user.setPassword(bc.encode(userVo.getPassword()));
        return this.userRepository.save(user);
    }

}
