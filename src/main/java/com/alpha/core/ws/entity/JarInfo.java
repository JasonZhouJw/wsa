package com.alpha.core.ws.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class JarInfo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200)
    private String path;

    @Column(length = 100)
    private String name;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime = new Date();

    @Column
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_info_id")
    private ServicesInfo servicesInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ServicesInfo getServicesInfo() {
        return servicesInfo;
    }

    public void setServicesInfo(ServicesInfo servicesInfo) {
        this.servicesInfo = servicesInfo;
    }

    @Override
    public String toString() {
        return "JarInfo{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", createdTime=" + createdTime +
                ", active=" + active +
                '}';
    }
}
