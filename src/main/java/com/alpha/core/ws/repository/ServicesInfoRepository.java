package com.alpha.core.ws.repository;

import com.alpha.core.ws.entity.ServicesInfo;
import com.alpha.core.ws.repository.search.SearchParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jzhou237 on 2016-09-29.
 */
@Repository("servicesInfoRepository")
public interface ServicesInfoRepository extends JpaRepository<ServicesInfo, Long> {

    @Query("select services from ServicesInfo services where active=true")
    List<ServicesInfo> findActive();

    @Query("select services from ServicesInfo services")
    List<ServicesInfo> search(SearchParam queryParam);

//    @Query("select servicesInfo from ServicesInfo servicesInfo where servicesInfo.wsdl.id=:wsdlId")
//    ServicesInfo findByWsdl(@Param("wsdlId") String id);
}
