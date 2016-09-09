package com.alpha.core.ws.server.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by jzhou237 on 9/8/2016.
 */
@Configuration
public class WebServicesFactory {

    @Autowired
    private Bus bus;

    @Resource(name = "ws")
    private Map<String, Object> wsMap;

    @Bean
    public List<Endpoint> endpoint() {
        List<Endpoint> endpointList = new ArrayList<Endpoint>();
        wsMap.forEach((address, wsObj) -> {
            Endpoint endpoint = new EndpointImpl(bus, wsObj);
            endpoint.publish(address);
            endpointList.add(endpoint);
        });
        return endpointList;
    }

}
