package com.alpha.core.ws.repository;

import com.alpha.core.ws.entity.InterfaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jzhou237 on 9/9/2016.
 */
@Repository
public interface InterfaceInfoRepository extends JpaRepository<InterfaceInfo, Long> {

    @Query("select interfaceInfo from InterfaceInfo interfaceInfo where interfaceInfo.wsdl.servicesInfo.id=:servicesId")
    List<InterfaceInfo> findByWsdl(@Param("servicesId") Long id);

}
