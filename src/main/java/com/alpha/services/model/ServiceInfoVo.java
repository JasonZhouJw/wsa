package com.alpha.services.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jzhou237 on 2016-10-07.
 */
@Getter
@Setter
@ToString
public class ServiceInfoVo {

    private Long id;

    private String wsdl;

    private String name;

    private Date updatedTime;

    private Boolean wsdl2java = false;

    private Boolean active = true;

    private List<MethodInfoVo> methodInfoList = new ArrayList<>();

    private Date createdTime = new Date();


}
