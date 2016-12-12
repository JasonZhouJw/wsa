package com.alpha.demo.server;

import com.alpha.demo.server.entity.User;
import org.springframework.stereotype.Component;

@Component
public class HelloImpl implements IHello {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public User say(User user) {
        return user;
    }
}
