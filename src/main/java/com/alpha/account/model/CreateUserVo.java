package com.alpha.account.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotNull;

/**
 * Created by jzhou237 on 2017-03-09.
 */
@ScriptAssert(lang = "javascript", script = "_this.getPassword()==_this.getRepeatPassword()", message = "password is not same.")
public class CreateUserVo extends UserVo {

    @Override
    @NotNull(message = "Name is null.")
    @NotEmpty(message = "account.create.result.name.empty")
    public String getName() {
        return super.getName();
    }

    @Override
    @NotEmpty
    @Length(min = 8, max = 20)
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    @NotEmpty
    @Length(min = 8, max = 20)
    public String getRepeatPassword() {
        return super.getRepeatPassword();
    }
}
