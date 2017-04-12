package com.alpha.services.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by jzhou237 on 2017-04-10.
 */
public class ServiceInfoCreateVo extends ServiceInfoVo {

    @NotEmpty
    @Override
    public String getWsdl() {
        return super.getWsdl();
    }

    @NotEmpty
    @Override
    public String getName() {
        return super.getName();
    }
}
