package com.alpha.core.ws.server;

import org.springframework.stereotype.Component;

@Component
public class HelloImpl implements IHello {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
