package com.alpha.services.entities;

import com.alpha.common.model.Option;
import com.alpha.services.model.MethodInfoVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.alpha.common.utils.Constants.DOT;

/**
 * Created by jzhou237 on 2016-12-12.
 */
@Entity
@Setter
@Getter
@ToString
public class MethodInfo {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String method;

    @ManyToOne
    @JoinColumn
    private ServicesInfo servicesInfo;

    public MethodInfo() {
    }

    public MethodInfo(Long methodId) {
        this.id = methodId;
    }

    public static List<MethodInfoVo> toVo(List<MethodInfo> methodInfoList, boolean hasChild) {
        List<MethodInfoVo> methodInfoVoList = new ArrayList<>();
        if (methodInfoList != null) {
            methodInfoList.forEach(methodInfo -> methodInfo.toVo(hasChild));
        }
        return methodInfoVoList;
    }

    public static List<Option> convert(List<MethodInfo> methodInfoList) {
        List<Option> optionList = new ArrayList<>();
        if (methodInfoList != null) {
            methodInfoList.forEach(methodInfo -> {
                optionList.add(new Option(methodInfo.getMethod(), String.valueOf(methodInfo.getId())));
            });
        }
        return optionList;
    }

    public MethodInfoVo toVo(boolean hasChild) {
        MethodInfoVo methodInfoVo = new MethodInfoVo();
        methodInfoVo.setId(this.id);
        methodInfoVo.setMethod(this.method);
        if (hasChild) {
            methodInfoVo.setServicesInfoVo(this.servicesInfo.toVo(false));
        }
        return methodInfoVo;
    }

    public String getMethodName() {
        return this.servicesInfo.getInterfaceClass() + DOT + this.method;
    }
}
