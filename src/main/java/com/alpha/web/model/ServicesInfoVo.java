package com.alpha.web.model;

import com.alpha.core.ws.entity.ServicesInfo;
import com.alpha.core.ws.utils.BeanCopier;
import com.alpha.core.ws.utils.ILog;
import com.alpha.core.ws.utils.enums.EnvType;
import com.alpha.core.ws.utils.enums.ProtocolType;

import java.util.Date;
import java.util.List;

/**
 * Created by jzhou237 on 2016-10-07.
 */
public class ServicesInfoVo implements ILog {

    private Long id;

    private String name;

    private String service;

    private EnvType type;

    private ProtocolType protocolType;

    private Date updatedTime;

    private String path;

    public static ServicesInfoVo toVo(ServicesInfo servicesInfo) {
        return (ServicesInfoVo) BeanCopier.copyBean(servicesInfo, ServicesInfoVo.class);
    }

    public static List<ServicesInfoVo> toVo(List<ServicesInfo> servicesInfoList) {
        return BeanCopier.copyBean(servicesInfoList, ServicesInfoVo.class);
    }

    public ServicesInfo toDo() {
        return (ServicesInfo) BeanCopier.copyBean(this, ServicesInfo.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(ProtocolType protocolType) {
        this.protocolType = protocolType;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
