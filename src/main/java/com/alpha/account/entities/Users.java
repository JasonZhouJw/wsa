package com.alpha.account.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by jzhou237 on 2016-12-05.
 */
@Data
@Entity
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 40, unique = true, nullable = false)
    private String name;

    @Column(length = 200)
    private String password;

    @OneToOne(mappedBy = "users")
    private Role role;
}