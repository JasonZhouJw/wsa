package com.alpha.core.ws.repository;

import com.alpha.core.ws.entity.InterfaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 9/9/2016.
 */
@Repository
public interface InterfaceInfoRepository extends JpaRepository<InterfaceInfo, Long> {
}
