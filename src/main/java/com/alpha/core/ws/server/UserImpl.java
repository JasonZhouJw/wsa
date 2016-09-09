package com.alpha.core.ws.server;

import org.springframework.stereotype.Service;

/**
 * Created by jzhou237 on 9/8/2016.
 */
@Service
public class UserImpl implements IUser {
    @Override
    public String say(String name) {
        return name + " said";
    }
}
