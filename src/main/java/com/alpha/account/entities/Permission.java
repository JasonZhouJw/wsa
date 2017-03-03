package com.alpha.account.entities;

import com.alpha.account.model.PermissionVo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jzhou237 on 2016-12-05.
 */
@Data
@Entity
public class Permission {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 40, unique = true)
    private String name;

    @Column(length = 200)
    private String url;


    public PermissionVo toVo() {
        PermissionVo permissionVo = new PermissionVo();
        permissionVo.setId(this.getId());
        permissionVo.setName(this.getName());
        permissionVo.setUrl(this.getUrl());
        return permissionVo;
    }

}
