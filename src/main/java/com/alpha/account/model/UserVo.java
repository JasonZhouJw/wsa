package com.alpha.account.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-12-06.
 */
@Getter
@Setter
public class UserVo {

    private Long id;

    @NotNull
    private String name;

    private String password;

    private String repeatPassword;

    private boolean isAdmin;

    private boolean active;

    private List<PermissionVo> permissionVoList = new ArrayList<>();
}
