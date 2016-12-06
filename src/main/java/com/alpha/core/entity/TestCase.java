package com.alpha.core.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jzhou237 on 9/16/2016.
 */
@Data
@Entity
@Table(indexes = {@Index(name = "idx_testcase_name_interface", columnList = "name,interface_id")})
public class TestCase {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "interface_id", nullable = false)
    private InterfaceInfo interfaceInfo;

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

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public InterfaceInfo getInterfaceInfo() {
//        return interfaceInfo;
//    }
//
//    public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
//        this.interfaceInfo = interfaceInfo;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getRequestValue() {
//        return requestValue;
//    }
//
//    public void setRequestValue(String requestValue) {
//        this.requestValue = requestValue;
//    }
//
//    public String getVerification() {
//        return verification;
//    }
//
//    public void setVerification(String verification) {
//        this.verification = verification;
//    }
//
//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//
//    public Date getCreatedTime() {
//        return createdTime;
//    }
//
//    public void setCreatedTime(Date createdTime) {
//        this.createdTime = createdTime;
//    }
//
//    public Date getUpdatedTime() {
//        return updatedTime;
//    }
//
//    public void setUpdatedTime(Date updatedTime) {
//        this.updatedTime = updatedTime;
//    }
}
