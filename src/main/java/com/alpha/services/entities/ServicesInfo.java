package com.alpha.services.entities;

import com.alpha.common.model.Option;
import com.alpha.services.model.ServicesInfoVo;
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
public class ServicesInfo {

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

    @Column(nullable = false)
    private String interfaceClass;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicesInfo")
    private List<MethodInfo> methodInfoList = new ArrayList<>();

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    public static List<ServicesInfoVo> toVo(List<ServicesInfo> serviceInfoList, boolean hasChild) {
        List<ServicesInfoVo> servicesInfoVoList = new ArrayList<>();
        if (serviceInfoList != null) {
            serviceInfoList.forEach(serviceInfo -> servicesInfoVoList.add(serviceInfo.toVo(hasChild)));
        }
        return servicesInfoVoList;
    }

    public static List<Option> convert(List<ServicesInfo> servicesInfoList) {
        List<Option> optionList = new ArrayList<>();
        if (servicesInfoList != null) {
            servicesInfoList.forEach(servicesInfo -> {
                optionList.add(new Option(servicesInfo.getInterfaceClass(), String.valueOf(servicesInfo.getId())));
            });
        }
        return optionList;
    }

    public ServicesInfoVo toVo(boolean hasChild) {
        ServicesInfoVo servicesInfoVo = new ServicesInfoVo();
        servicesInfoVo.setId(this.id);
        servicesInfoVo.setWsdl(this.wsdl);
        servicesInfoVo.setName(this.name);
        servicesInfoVo.setWsdl2java(this.wsdl2java);
        servicesInfoVo.setActive(this.active);
        servicesInfoVo.setUpdatedTime(this.updatedTime);
        servicesInfoVo.setCreatedTime(this.createdTime);
        if (hasChild) {
            servicesInfoVo.setMethodInfoList(MethodInfo.toVo(this.methodInfoList, false));
        }
        return servicesInfoVo;
    }

    public Example<ServicesInfo> getExample() {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("createdTime", "updatedTime");
        return Example.of(this, matcher);
    }
}
