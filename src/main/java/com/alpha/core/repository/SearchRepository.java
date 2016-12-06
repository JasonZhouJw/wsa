package com.alpha.core.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by jzhou237 on 2016-11-25.
 */
@Repository
public class SearchRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

}
