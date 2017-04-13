package com.alpha.services.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by jzhou237 on 2017-04-11.
 */
public class ServiceInfoUpdateVo extends ServiceInfoVo {

    @NotNull
    @Override
    public Long getId() {
        return super.getId();
    }

    @NotEmpty
    @Override
    public String getWsdl() {
        return super.getWsdl();
    }
}
