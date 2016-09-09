package com.alpha.core.ws.server;

import javax.jws.WebService;

/**
 * Created by jzhou237 on 9/8/2016.
 */
@WebService
public interface IHello {

    public String sayHello(String name);
}
