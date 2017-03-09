package com.alpha.account.domain;

import com.alpha.account.exception.UserException;
import com.alpha.account.model.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.function.Consumer;

/**
 * Created by jzhou237 on 2017-03-02.
 */
public interface IUser {
    com.alpha.account.entities.User login(String name, String password);

    com.alpha.account.entities.User create(UserVo userVo) throws UserException;

    void findAll(com.alpha.account.entities.User user,Pageable pageable, Consumer<Page<com.alpha.account.entities.User>> consumer);

    com.alpha.account.entities.User findById(Long id);

}
