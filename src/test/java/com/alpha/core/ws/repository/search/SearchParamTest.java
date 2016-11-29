package com.alpha.core.ws.repository.search;

import org.junit.Test;
import org.springframework.data.jpa.domain.Specifications;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jzhou237 on 2016-11-28.
 */
public class SearchParamTest {
    @Test
    public void and() throws Exception {

    }

    @Test
    public void or() throws Exception {

    }

    @Test
    public void addParam() throws Exception {

    }

    @Test
    public void search() throws Exception {
        SearchParam searchParam = new SearchParam("name", "where").and("name", "and").or("name", "or");
        Specifications specifications = searchParam.search();
        assertNotNull(specifications);
    }

}