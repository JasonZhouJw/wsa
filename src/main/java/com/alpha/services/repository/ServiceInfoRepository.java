package com.alpha.services.repository;

import com.alpha.services.entities.ServiceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 2016-12-12.
 */
@Repository
public interface ServicesInfoRepository extends JpaRepository<ServiceInfo, Long> {
}
