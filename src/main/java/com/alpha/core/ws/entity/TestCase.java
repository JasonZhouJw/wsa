package com.alpha.core.ws.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.DETACH)
    private List<RequestInfo> requestInfoList = new ArrayList<RequestInfo>();

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<VerifyInfo> verifyList = new ArrayList<VerifyInfo>();

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

    public List<VerifyInfo> getVerifyList() {
        return verifyList;
    }

    public void setVerifyList(List<VerifyInfo> verifyList) {
        this.verifyList = verifyList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
