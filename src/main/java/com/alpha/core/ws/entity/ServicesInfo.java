package com.alpha.core.ws.entity;

import com.alpha.core.ws.utils.enums.EnvType;
import com.alpha.core.ws.utils.enums.ProtocolType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ServicesInfo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200)
    private String aliasName;

    @Column(length = 400, nullable = false, unique = true)
    private String service;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private EnvType type;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ProtocolType protocolType;

    @Column
    private boolean active = true;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    @Column(length = 200)
    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public EnvType getType() {
        return type;
    }

    public void setType(EnvType type) {
        this.type = type;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(ProtocolType protocolType) {
        this.protocolType = protocolType;
    }
}
