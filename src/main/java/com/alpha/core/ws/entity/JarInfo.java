package com.alpha.core.ws.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jzhou237 on 2016-09-23.
 */
@Entity
public class JarInfo {

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
}
