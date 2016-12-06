package com.alpha.core.repository;

import com.alpha.core.entity.TestCase;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-11-28.
 */
@Repository
public class TestCaseSearchRepository extends SearchRepository<TestCase> {


    public List<TestCase> search(TestCase testCase) {
        List<TestCase> result = new ArrayList<TestCase>();
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<TestCase> query = criteriaBuilder.createQuery(TestCase.class);
        Root<TestCase> root = query.from(TestCase.class);

        return result;
    }
}
