package com.alpha.core.ws.entity;

import com.alpha.core.ws.utils.enums.OperationType;

import javax.persistence.*;

/**
 * Created by jzhou237 on 9/12/2016.
 */
@Entity
public class VerifyInfo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length=200)
    private String expectValue;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="field_id")
    private FieldInfo filedInfo;

    @Column
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "validation_id")
    private Validation validation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpectValue() {
        return expectValue;
    }

    public void setExpectValue(String expectValue) {
        this.expectValue = expectValue;
    }

    public FieldInfo getFiledInfo() {
        return filedInfo;
    }

    public void setFiledInfo(FieldInfo filedInfo) {
        this.filedInfo = filedInfo;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
