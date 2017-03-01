package com.alpha.account.entities;

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

}
