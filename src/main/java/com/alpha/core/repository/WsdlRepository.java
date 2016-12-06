package com.alpha.core.repository;

import com.alpha.core.entity.Wsdl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 9/9/2016.
 */
@Repository
public interface WsdlRepository extends JpaRepository<Wsdl, Long> {

    @Modifying
    @Query("update Wsdl set active=false where servicesInfo.id=:servicesId")
    int inactive(long servicesId);
}
