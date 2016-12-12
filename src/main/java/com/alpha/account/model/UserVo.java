package com.alpha.account.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jzhou237 on 2016-12-06.
 */
@Getter
@Setter
public class UserVo {

    private Long id;

    private String name;

    private String password;

    private RoleVo role;
}
