package com.alpha.services.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jzhou237 on 2016-12-12.
 */
@Entity
@Setter
@Getter
@ToString
public class ServicesInfo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String wsdl;

    @Column
    private boolean wsdl2java = false;

    @Column
    private boolean active = false;

    @Column(nullable = false)
    private String interfaceClass;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MethodInfo> methodInfoList = new ArrayList<>();

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;
}
