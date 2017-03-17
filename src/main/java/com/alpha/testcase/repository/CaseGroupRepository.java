package com.alpha.testcase.repository;

import com.alpha.testcase.entities.CaseGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 2017-03-17.
 */
@Repository
public interface CaseGroupRepository extends JpaRepository<CaseGroup, Long> {
}
