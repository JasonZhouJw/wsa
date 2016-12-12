package com.alpha.services.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jzhou237 on 2016-12-12.
 */
@Entity
@Setter
@Getter
@ToString
public class MethodInfo {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String method;

    @ManyToOne
    @JoinColumn
    private ServicesInfo servicesInfo;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;
}
