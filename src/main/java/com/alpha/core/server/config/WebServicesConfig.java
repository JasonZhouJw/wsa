package com.alpha.core.server.config;

import com.alpha.core.server.IHello;
import com.alpha.core.server.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzhou237 on 9/8/2016.
 */
@Configuration
public class WebServicesConfig {

    @Autowired
    private IHello hello;

    @Autowired
    private IUser user;

    @Bean
    public Map<String, Object> ws() {
        Map<String, Object> wsMap = new HashMap<String, Object>();
        wsMap.put("/hello", this.hello);
        wsMap.put("/user", this.user);
        return wsMap;
    }

}
