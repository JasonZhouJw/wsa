package com.alpha.services.model;

import com.alpha.common.enums.ProtocolType;
import com.alpha.common.utils.BeanCopier;
import com.alpha.services.entities.ServicesInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Created by jzhou237 on 2016-10-07.
 */
@Getter
@Setter
@ToString
public class ServicesInfoVo {

    private Long id;

    private String name;

    private String service;

    private ProtocolType protocolType;

    private Date updatedTime;

    private String path;

    public static ServicesInfoVo toVo(ServicesInfo servicesInfo) {
        return (ServicesInfoVo) BeanCopier.copyBean(servicesInfo, ServicesInfoVo.class);
    }

    public static List<ServicesInfoVo> toVo(List<ServicesInfo> servicesInfoList) {
        return BeanCopier.copyBean(servicesInfoList, ServicesInfoVo.class);
    }

}
