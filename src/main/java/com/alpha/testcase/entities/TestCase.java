package com.alpha.testcase.entities;

import com.alpha.services.entities.MethodInfo;
import com.alpha.testcase.model.TestCaseVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jzhou237 on 9/16/2016.
 */

@ToString
@Entity
//@Table(indexes = {@Index(name = "idx_testcase_name_interface", columnList = "name,interface_id")})
@Getter
@Setter
public class TestCase {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "method_info_id", nullable = false)
    private MethodInfo methodInfo;

    @Column(length = 4000)
    private String requestValue;// TODO: 9/20/2016 should be consider the data length

    @Column(length = 4000)
    private String verification;// TODO: 9/20/2016 should be consider the data length

    @ManyToOne
    @JoinColumn(name = "groupId")
    private CaseGroup caseGroup;

    @Column
    private boolean active = true;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    public TestCase(TestCaseVo testCaseVo) {
        if (testCaseVo == null) {
            return;
        }
        this.id = testCaseVo.getId();
        this.name = testCaseVo.getName();
        this.requestValue = testCaseVo.getRequestValue();
        this.verification = testCaseVo.getVerification();
        this.caseGroup = new CaseGroup(testCaseVo.getGroupId());
        this.methodInfo = new MethodInfo(testCaseVo.getMethodId());
        this.active = testCaseVo.isActive();
    }

    public TestCase() {

    }

    public static List<TestCaseVo> toVo(List<TestCase> testCaseList) {
        List<TestCaseVo> testCaseVoList = new ArrayList<>();
        if (testCaseList != null) {
            testCaseList.forEach((testCase -> testCaseVoList.add(testCase.toVo())));
        }
        return testCaseVoList;
    }

    public TestCaseVo toVo() {
        TestCaseVo testCaseVo = new TestCaseVo();
        testCaseVo.setId(this.id);
        testCaseVo.setName(this.name);
        testCaseVo.setGroupId(this.caseGroup.getId());
        testCaseVo.setMethodId(this.methodInfo.getId());
        testCaseVo.setActive(this.active);
        testCaseVo.setMethodName(this.methodInfo.getMethodName());
        testCaseVo.setGroupName(this.caseGroup.getName());
        return testCaseVo;
    }

    public Example<TestCase> getExample() {
        return Example.of(this, ExampleMatcher.matching().withIgnorePaths("createdTime", "updatedTime", "caseGroup", "methodInfo"));
    }

}
