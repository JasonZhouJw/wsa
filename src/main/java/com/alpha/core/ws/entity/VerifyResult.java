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
    private Date executedTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    @ManyToOne
    @JoinColumn(name = "verify_info_id")
    private VerifyInfo verifyInfo;

    @Column
    private boolean result;

    @Column(length = 400)
    private String message;

    @Column(length = 400)
    private String actualValue;

    public VerifyResult() {
    }

    public VerifyResult(VerifyInfo verifyInfo) {
        this.verifyInfo = verifyInfo;
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

    public VerifyInfo getVerifyInfo() {
        return verifyInfo;
    }

    public void setVerifyInfo(VerifyInfo verifyInfo) {
        this.verifyInfo = verifyInfo;
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

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }
}
