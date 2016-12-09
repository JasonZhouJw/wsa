package com.alpha.core.wsdl2java.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzhou237 on 2016-12-09.
 */
@Setter
@Getter
public class Services {

    /**
     * interface class name
     */
    private String interfaceClass;

    /**
     * service class name
     */
    private String servicesName;

    /**
     * method information
     */
    private Map<String, ServiceMethod> methodArgumentsMap = new HashMap<>();

}