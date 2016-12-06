package com.alpha.web.services.model;

import com.alpha.core.entity.InterfaceInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-12-06.
 */
@Setter
@Getter
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

}
