package com.alpha.testcase.entities;

import com.alpha.services.entities.MethodInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jzhou237 on 9/16/2016.
 */

@ToString
@Entity
//@Table(indexes = {@Index(name = "idx_testcase_name_interface", columnList = "name,interface_id")})
@Getter
@Setter
public class TestCase {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 200, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "method_info_id", nullable = false)
    private MethodInfo methodInfo;

    @Column(length = 4000)
    private String requestValue;// TODO: 9/20/2016 should be consider the data length

    @Column(length = 4000)
    private String verification;// TODO: 9/20/2016 should be consider the data length

    @Column
    private boolean active = true;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

}
