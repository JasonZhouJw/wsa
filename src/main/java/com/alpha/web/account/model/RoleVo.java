package com.alpha.web.account.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-12-06.
 */
@Getter
@Setter
public class RoleVo {

    private Long id;

    private String name;

    private List<PermissionVo> permissionVoList = new ArrayList<>();
}
