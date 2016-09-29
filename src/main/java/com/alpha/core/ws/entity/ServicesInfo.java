package com.alpha.core.ws.entity;

import com.alpha.core.ws.utils.enums.EnvType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ServicesInfo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200)
    private String name;

    @Column(length = 400, nullable = false, unique = true)
    private String service;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private EnvType type;

    @Column
    private boolean active = true;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    @OneToOne
    private JarInfo jarInfo;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JarInfo getJarInfo() {
        return jarInfo;
    }

    public void setJarInfo(JarInfo jarInfo) {
        this.jarInfo = jarInfo;
    }
}
