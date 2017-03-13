package com.alpha.account.domain;

import com.alpha.account.entities.Permission;
import com.alpha.account.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jzhou237 on 2017-03-07.
 */
@Component
public class PermissionDo implements IPermissionDo {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return this.permissionRepository.findAll();
    }
}
