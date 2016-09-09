package com.alpha.core.ws.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by jzhou237 on 9/8/2016.
 */
@Entity
public class Demo implements Serializable {

    @Id
    @GeneratedValue
    public Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
