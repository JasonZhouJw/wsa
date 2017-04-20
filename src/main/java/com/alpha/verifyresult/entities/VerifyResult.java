package com.alpha.verifyresult.entities;

import com.alpha.common.enums.ResultType;
import com.alpha.testcase.entities.TestCase;
import com.alpha.verifyresult.model.VerifyResultVo;
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
@Getter
@Setter
@ToString
@Entity
public class VerifyResult {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "test_case_id")
    private TestCase testCase;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ResultType result = ResultType.SUCCESS;

    @Column(length = 4000)
    private String message;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date executedTime = new Date();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime = new Date();

    public static List<VerifyResultVo> toVo(List<VerifyResult> verifyResultList) {
        List<VerifyResultVo> verifyResultVoList = new ArrayList<>();
        if (verifyResultList != null) {
            verifyResultList.forEach(verifyResult -> verifyResultVoList.add(verifyResult.toVo()));
        }
        return verifyResultVoList;
    }

    public VerifyResultVo toVo() {
        VerifyResultVo verifyResultVo = new VerifyResultVo();
        verifyResultVo.setId(this.id);
        verifyResultVo.setExecutedTime(this.executedTime);
        verifyResultVo.setMessage(this.message);
        verifyResultVo.setResult(this.result);
        verifyResultVo.setUpdatedTime(this.updatedTime);
        verifyResultVo.setTestCase(this.testCase.toVo());
        return verifyResultVo;
    }

    public Example<VerifyResult> getExample() {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("updatedTime", "executedTime");
        return Example.of(this, exampleMatcher);
    }
}
