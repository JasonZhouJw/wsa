package com.alpha.core.wsdl2java.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-12-09.
 */
@Setter
@Getter
public class ServiceMethod {

    private String method;

    private List<String> arguments = new ArrayList<>();

    private String returnClass;

}
