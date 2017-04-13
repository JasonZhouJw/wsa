package com.cucumber.data.serviceinfo;

import com.alpha.services.entities.ServiceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 2017-04-10.
 */
@Repository
public interface ServiceInfoForTestRepository extends JpaRepository<ServiceInfo, Long> {

    @Modifying
    @Query("delete from ServiceInfo serviceInfo where serviceInfo.wsdl=:wsdl")
    void deleteByWsdl(@Param("wsdl") String wsdl);
}
