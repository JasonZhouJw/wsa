package com.alpha.account.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by jzhou237 on 2017-03-09.
 */
public class CreateUserVo extends UserVo {

    @Override
    @NotNull(message = "Name is null.")
    @NotEmpty(message = "account.create.result.name.empty")
    public String getName() {
        return super.getName();
    }
}
