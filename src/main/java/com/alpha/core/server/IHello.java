package com.alpha.core.server;

import com.alpha.core.server.entity.User;

import javax.jws.WebService;

/**
 * Created by jzhou237 on 9/8/2016.
 */
@WebService
public interface IHello {

    String sayHello(String name);

    User say(User user);


}
