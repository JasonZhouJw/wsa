package com.alpha.testcase.repository;

import com.alpha.testcase.entities.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 9/16/2016.
 */
@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
    TestCase findByName(String name);
}
