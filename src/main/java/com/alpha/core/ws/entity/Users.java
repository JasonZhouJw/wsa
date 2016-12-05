package com.alpha.core.ws.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by jzhou237 on 2016-12-05.
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 40, unique = true, nullable = false)
    private String name;

    @Column(length = 200)
    private String password;

    @OneToOne(mappedBy = "user")
    private Role role;
}
