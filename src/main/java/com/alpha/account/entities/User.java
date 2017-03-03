package com.alpha.account.entities;

import com.alpha.account.model.PermissionVo;
import com.alpha.account.model.UserVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-12-05.
 */

@Entity
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 60, unique = true, nullable = false)
    private String name;

    @Column(length = 200)
    private String password;

//    @Column(length = 11, unique = true, nullable = false)
//    private String phone;

    @ManyToMany(targetEntity = Permission.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<Permission> permissions = new ArrayList<>();


    public UserVo toVo() {
        UserVo userVo = new UserVo();
        userVo.setName(this.getName());
        userVo.setId(this.getId());
        userVo.setPermissionVoList(new ArrayList<PermissionVo>());
        permissions.forEach(permission -> userVo.getPermissionVoList().add(permission.toVo()));
        return userVo;
    }
}
