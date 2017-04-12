package com.alpha.services.entities;

import com.alpha.common.model.Option;
import com.alpha.services.model.ServiceInfoUpdateVo;
import com.alpha.services.model.ServiceInfoVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jzhou237 on 2016-12-12.
 */
@Entity
@Setter
@Getter
@ToString
public class ServiceInfo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String wsdl;

    @Column(length = 200)
    private String name;

    @Column
    private Boolean wsdl2java = false;

    @Column
    private Boolean active = true;

    @Column
    private String interfaceClass;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceInfo")
    private List<MethodInfo> methodInfoList = new ArrayList<>();

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    public static List<ServiceInfoVo> toVo(List<ServiceInfo> serviceInfoList, boolean hasChild) {
        List<ServiceInfoVo> serviceInfoVoList = new ArrayList<>();
        if (serviceInfoList != null) {
            serviceInfoList.forEach(serviceInfo -> serviceInfoVoList.add(serviceInfo.toVo(hasChild)));
        }
        return serviceInfoVoList;
    }

    public static List<Option> convert(List<ServiceInfo> serviceInfoList) {
        List<Option> optionList = new ArrayList<>();
        if (serviceInfoList != null) {
            serviceInfoList.forEach(servicesInfo -> {
                optionList.add(new Option(servicesInfo.getInterfaceClass(), String.valueOf(servicesInfo.getId())));
            });
        }
        return optionList;
    }

    public static ServiceInfo valueOf(ServiceInfoVo serviceInfoVo) {
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setName(serviceInfoVo.getName());
        serviceInfo.setWsdl(serviceInfoVo.getWsdl());
        serviceInfo.setUpdatedTime(new Date());
        serviceInfo.setActive(serviceInfoVo.getActive());
        serviceInfo.setWsdl2java(serviceInfoVo.getWsdl2java());
        return serviceInfo;
    }

    public ServiceInfoVo toVo(boolean hasChild) {
        ServiceInfoVo serviceInfoVo = new ServiceInfoVo();
        serviceInfoVo.setId(this.id);
        serviceInfoVo.setWsdl(this.wsdl);
        serviceInfoVo.setName(this.name);
        serviceInfoVo.setWsdl2java(this.wsdl2java);
        serviceInfoVo.setActive(this.active);
        serviceInfoVo.setUpdatedTime(this.updatedTime);
        serviceInfoVo.setCreatedTime(this.createdTime);
        if (hasChild) {
            serviceInfoVo.setMethodInfoList(MethodInfo.toVo(this.methodInfoList, false));
        }
        return serviceInfoVo;
    }

    public Example<ServiceInfo> getExample() {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("createdTime", "updatedTime");
        return Example.of(this, matcher);
    }

    public void update() {
        this.updatedTime = new Date();
    }

    public void updateValue(ServiceInfoUpdateVo serviceInfoVo) {
        this.setName(serviceInfoVo.getName());
        this.setWsdl(serviceInfoVo.getWsdl());
        this.setActive(serviceInfoVo.getActive());
        this.setWsdl2java(serviceInfoVo.getWsdl2java());
        update();
    }
}
