package com.alpha.services.repository;

import com.alpha.services.entities.ServiceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

/**
 * Created by jzhou237 on 2016-12-12.
 */
@Repository
public interface ServiceInfoRepository extends JpaRepository<ServiceInfo, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select serviceInfo from ServiceInfo serviceInfo where serviceInfo.id=:id")
    ServiceInfo findOneWithLock(@Param("id") Long id);

    ServiceInfo findByWsdl(String wsdl);
}
