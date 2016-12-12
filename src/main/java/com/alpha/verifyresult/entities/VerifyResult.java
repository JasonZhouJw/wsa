package com.alpha.verifyresult.entities;

import com.alpha.common.enums.ResultType;
import com.alpha.services.entities.MethodInfo;
import com.alpha.testcase.entities.TestCase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jzhou237 on 9/16/2016.
 */
@Getter
@Setter
@ToString
@Entity
public class VerifyResult {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "test_case_id")
    private TestCase testCase;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "method_info_id")
    private MethodInfo methodInfo;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ResultType result = ResultType.SUCCESS;

    @Column(length = 4000)
    private String message;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date executedTime = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime = new Date();

}
