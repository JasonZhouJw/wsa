package com.alpha.demo.server;

import javax.jws.WebService;

/**
 * Created by jzhou237 on 9/8/2016.
 */
@WebService
public interface IUser {

    String say(String name);
}
