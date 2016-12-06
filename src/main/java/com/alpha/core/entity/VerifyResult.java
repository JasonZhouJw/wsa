package com.alpha.core.entity;

import com.alpha.core.common.utils.enums.ResultType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jzhou237 on 9/16/2016.
 */
@Data
@NoArgsConstructor
@Entity
public class VerifyResult {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date executedTime = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime = new Date();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "test_case_id")
    private TestCase testCase;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "interface_id")
    private InterfaceInfo interfaceInfo;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ResultType result = ResultType.SUCCESS;

    @Column(length = 4000)
    private String message;

    public VerifyResult(TestCase testCase) {
        this.testCase = testCase;
    }


}
