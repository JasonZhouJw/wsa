package com.alpha.testcase.repository;

import com.alpha.testcase.entities.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-11-28.
 */
@Repository
public class TestCaseSearchRepository {

    @Autowired
    private EntityManager entityManager;

    public List<TestCase> search(TestCase testCase) {
        List<TestCase> result = new ArrayList<TestCase>();
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<TestCase> query = criteriaBuilder.createQuery(TestCase.class);
        Root<TestCase> root = query.from(TestCase.class);

        return result;
    }
}
