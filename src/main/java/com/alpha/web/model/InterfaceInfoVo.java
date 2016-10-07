package com.alpha.web.model;

import com.alpha.core.ws.entity.InterfaceInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-10-06.
 */
public class InterfaceInfoVo {

    private long id;

    private String methodName;

    public static InterfaceInfoVo toVo(InterfaceInfo interfaceInfo) {
        InterfaceInfoVo vo = new InterfaceInfoVo();
        vo.setId(interfaceInfo.getId());
        vo.setMethodName(interfaceInfo.getMethodName());
        return vo;
    }

    public static List<InterfaceInfoVo> toVo(List<InterfaceInfo> interfaceInfoList) {
        List<InterfaceInfoVo> voList = new ArrayList<InterfaceInfoVo>();
        interfaceInfoList.forEach(interfaceInfo -> {
            voList.add(toVo(interfaceInfo));
        });
        return voList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
