package com.alpha.testcase.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2017-03-16.
 */
@Setter
@Getter
@Entity
public class CaseGroup {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, unique = true)
    private String name;

    @Column
    private boolean active = true;

    @OneToMany(mappedBy = "caseGroup")
    private List<TestCase> testCaseList = new ArrayList<>();


    public CaseGroup(Long groupId) {
        this.id = groupId;
    }

    public CaseGroup() {

    }
}
