package com.alpha.account.domain;

import com.alpha.account.entities.Permission;

import java.util.List;

/**
 * Created by jzhou237 on 2017-03-07.
 */
public interface IPermissionDo {

    List<Permission> findAll();
}
