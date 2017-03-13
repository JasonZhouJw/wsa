package com.alpha.account.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotNull;

/**
 * Created by jzhou237 on 2017-03-13.
 */
@ScriptAssert(lang = "javascript", script = "_this.getPassword()==_this.getRepeatPassword()", message = "result.password.not.same")
public class ChangePasswordVo extends UserVo {

    @Override
    @NotNull
    public Long getId() {
        return super.getId();
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
