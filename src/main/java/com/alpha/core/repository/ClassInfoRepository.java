package com.alpha.core.repository;

import com.alpha.core.entity.ClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 9/9/2016.
 */
@Repository
public interface ClassInfoRepository extends JpaRepository<ClassInfo, Long> {
}
