package com.alpha.core.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-12-05.
 */
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200)
    private String name;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.REFRESH)
    private Users users;

    @JoinColumn(name = "permission_id")
    @ManyToOne(targetEntity = Permission.class, cascade = CascadeType.REFRESH)
    private List<Permission> permissions = new ArrayList<>();
}
