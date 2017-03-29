package com.alpha.testcase.entities;

import com.alpha.testcase.model.CaseGroupVo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Example;

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
    private Boolean active = true;

    @OneToMany(mappedBy = "caseGroup")
    private List<TestCase> testCaseList = new ArrayList<>();


    public CaseGroup(Long groupId) {
        this.id = groupId;
    }

    public CaseGroup() {

    }

    public static List<CaseGroupVo> toVo(List<CaseGroup> caseGroupList) {
        List<CaseGroupVo> caseGroupVoList = new ArrayList<>();
        if (caseGroupList != null) {
            caseGroupList.forEach(caseGroup -> caseGroupVoList.add(caseGroup.toVo()));
        }
        return caseGroupVoList;
    }

    public Example<CaseGroup> getExample() {
        return Example.of(this);
    }

    public CaseGroupVo toVo() {
        CaseGroupVo caseGroupVo = new CaseGroupVo();
        caseGroupVo.setActive(this.active);
        caseGroupVo.setId(this.id);
        caseGroupVo.setName(this.name);
        return caseGroupVo;
    }
}
