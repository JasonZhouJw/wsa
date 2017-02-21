package com.alpha.account.domain;

import com.alpha.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jzhou237 on 2016-12-06.
 */
@Component
public class User {

    @Autowired
    private UserRepository userRepository;

    public com.alpha.account.entities.User login(String name, String password) {
        return this.userRepository.login(name, password);
    }

}
