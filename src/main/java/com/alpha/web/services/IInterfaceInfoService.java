package com.alpha.web.services;

import com.alpha.core.ws.entity.InterfaceInfo;

import java.util.List;

/**
 * Created by jzhou237 on 2016-10-06.
 */
public interface IInterfaceInfoService {

    List<InterfaceInfo> findAll();

    InterfaceInfo save(InterfaceInfo interfaceInfo);
}
