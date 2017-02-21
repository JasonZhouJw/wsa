package com.alpha.account.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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

    @Column(length = 11, unique = true, nullable = false)
    private String phone;

    @OneToOne(mappedBy = "user")
    private Role role;
}
