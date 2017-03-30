package com.alpha.testcase.domain;

import com.alpha.testcase.entities.CaseGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jzhou237 on 2017-03-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class CaseGroupImplTest {

    @Autowired
    private ICaseGroup caseGroup;

    private CaseGroup prepared;

    @Before
    public void setup() throws Exception {
        CaseGroup caseGroup = new CaseGroup();
        caseGroup.setName("New1");
        prepared = this.caseGroup.save(caseGroup);
        caseGroup = new CaseGroup();
        caseGroup.setName("New2");
        caseGroup.setActive(false);
        this.caseGroup.save(caseGroup);
    }

    @Test
    public void save() throws Exception {
        CaseGroup caseGroup = new CaseGroup();
        caseGroup.setName("New");
        CaseGroup saved = this.caseGroup.save(caseGroup);
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    public void findAllActive() throws Exception {
        List<CaseGroup> actual = this.caseGroup.findAllActive();
        assertEquals(1, actual.size());
    }

    @Test
    public void findAll() throws Exception {
        List<CaseGroup> actual = this.caseGroup.findAll();
        assertEquals(2, actual.size());
    }

    @Test
    public void inactive() throws Exception {
        CaseGroup caseGroup = new CaseGroup();
        caseGroup.setId(prepared.getId());
        try {
            this.caseGroup.inactive(caseGroup);
        } catch (Exception e) {
            fail();
        }
    }

}