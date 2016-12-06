package com.alpha.core.entity;

import com.alpha.core.common.utils.Constants;
import com.alpha.core.common.utils.enums.EnvType;
import com.alpha.core.common.utils.enums.ProtocolType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class ServicesInfo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200)
    private String aliasName;

    @Column(length = 400, nullable = false, unique = true, updatable = false)
    private String service;

    @Column(length = 20)
    private boolean isWsdl = false;

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

    public void setService(String service) {
        if (this.service.endsWith(Constants.WSDL_END)) {
            this.isWsdl = true;
        }
        this.service = service;
    }

}
