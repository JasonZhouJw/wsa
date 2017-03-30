package com.alpha.testcase.repository;

import com.alpha.testcase.entities.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 9/16/2016.
 */
@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
    TestCase findByName(String name);

    @Modifying
    @Query("update TestCase testCase set testCase.active = 0 where testCase.caseGroup.id = :groupId")
    void inactiveByGroup(@Param("groupId") Long groupId);
}
