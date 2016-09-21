package com.alpha.core.ws.entity;

import javax.persistence.*;

/**
 * Created by jzhou237 on 9/16/2016.
 */
@Entity
public class TestCase {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "interface_id")
    private InterfaceInfo interfaceInfo;

    @Column(length = 4000)
    private String requestValue;// TODO: 9/20/2016 should be consider the data length

    @Column(length = 4000)
    private String verification;// TODO: 9/20/2016 should be consider the data length

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InterfaceInfo getInterfaceInfo() {
        return interfaceInfo;
    }

    public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
        this.interfaceInfo = interfaceInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestValue() {
        return requestValue;
    }

    public void setRequestValue(String requestValue) {
        this.requestValue = requestValue;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
}
