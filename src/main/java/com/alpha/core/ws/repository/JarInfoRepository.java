package com.alpha.core.ws.repository;

import com.alpha.core.ws.entity.JarInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 2016-09-29.
 */
@Repository
public interface JarInfoRepository extends JpaRepository<JarInfo, Long> {
}
