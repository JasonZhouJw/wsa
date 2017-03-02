package com.alpha.account.domain;

import com.alpha.account.exception.UserException;
import com.alpha.account.model.UserVo;

/**
 * Created by jzhou237 on 2017-03-02.
 */
public interface IUser {
    com.alpha.account.entities.User login(String name, String password);


    com.alpha.account.entities.User create(UserVo userVo) throws UserException;
}
