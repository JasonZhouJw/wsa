package com.alpha.account.domain;

import com.alpha.account.entities.User;
import com.alpha.account.exception.UserException;
import com.alpha.account.exception.UserNotFoundException;
import com.alpha.account.exception.UserPasswordException;
import com.alpha.account.model.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.function.Consumer;

/**
 * Created by jzhou237 on 2017-03-02.
 */
public interface IUser {
    User login(String name, String password);

    User create(UserVo userVo) throws UserException;

    void findAll(User user, Pageable pageable, Consumer<Page<User>> consumer);

    User findById(Long id);

    User update(UserVo userVo) throws UserNotFoundException;

    User changePassword(UserVo userVo) throws UserNotFoundException, UserPasswordException;
}
