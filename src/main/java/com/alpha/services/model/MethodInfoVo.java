package com.alpha.services.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jzhou237 on 2017-03-20.
 */
@Setter
@Getter
public class MethodInfoVo {

    private Long id;

    private String method;

    private ServiceInfoVo serviceInfoVo;
}
