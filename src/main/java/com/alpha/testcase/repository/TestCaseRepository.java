package com.alpha.testcase.repository;

import com.alpha.testcase.entities.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jzhou237 on 9/16/2016.
 */
@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

    @Query("select testCase from TestCase testCase where testCase.active=true")
    List<TestCase> findAllActive();
}
