package com.alpha.core.ws.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jzhou237 on 9/16/2016.
 */
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
    private Date updatedTime;

    @ManyToOne
    @JoinColumn(name = "test_case_id")
    private TestCase testCase;

    @Column
    private boolean result=true;

    @Column(length = 4000)
    private String message;

    public VerifyResult() {
    }

    public VerifyResult(TestCase testCase) {
        this.testCase = testCase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExecutedTime() {
        return executedTime;
    }

    public void setExecutedTime(Date executedTime) {
        this.executedTime = executedTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }
}
