package com.alpha.verifyresult.domain;

import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.verifyresult.entities.VerifyResult;
import com.alpha.verifyresult.model.VerifyResultSearchVo;
import com.alpha.verifyresult.repository.VerifyResultRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 2016-11-25.
 */
@Component
public class VerifyResultImpl implements IVerifyResult {
    @Autowired
    private VerifyResultRepository verifyResultRepository;

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void saveResult(Map<String, VerifyResult> resultMap) {
        resultMap.forEach((key, verifyResult) -> {
            this.verifyResultRepository.save(verifyResult);
        });
    }

    @Override
    public void findAll(Pageable pageable, Consumer<Page<VerifyResult>> consumer) {
        Page<VerifyResult> verifyResultPage = this.verifyResultRepository.findAll(pageable);
        if (verifyResultPage != null) {
            Hibernate.initialize(verifyResultPage);
            consumer.accept(verifyResultPage);
        }
    }

    @Override
    public VerifyResult findOne(Long id) throws DataNotFoundException {
        VerifyResult verifyResult = this.verifyResultRepository.findOne(id);
        if (verifyResult == null) {
            throw new DataNotFoundException();
        }
        return verifyResult;
    }

    @Override
    public void search(VerifyResultSearchVo verifyResultVo, Pageable pageable, Consumer<Page<VerifyResult>> consumer) {
        Page<VerifyResult> verifyResultPage = this.verifyResultRepository.findAll(this.getWhereClause(verifyResultVo), pageable);
        if (verifyResultPage != null) {
            Hibernate.initialize(verifyResultPage);
            consumer.accept(verifyResultPage);
        }
    }

    private Specification<VerifyResult> getWhereClause(final VerifyResultSearchVo verifyResultSearchVo) {
        return new Specification<VerifyResult>() {
            @Override
            public Predicate toPredicate(Root<VerifyResult> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if (verifyResultSearchVo.getStartExecutedTime() != null) {
                    predicate.add(cb.greaterThanOrEqualTo(root.get("executedTime").as(Date.class), verifyResultSearchVo.getStartExecutedTime()));
                }
                if (verifyResultSearchVo.getEndExecutedTime() != null) {
                    predicate.add(cb.lessThanOrEqualTo(root.get("executedTime").as(Date.class), verifyResultSearchVo.getEndExecutedTime()));
                }
                if (verifyResultSearchVo.getTestCase() != null && verifyResultSearchVo.getTestCase().getId() > 0) {
                    predicate.add(cb.equal(root.get("test_case_id"), verifyResultSearchVo.getTestCase().getId()));
                }
                if (verifyResultSearchVo.getResult() != null) {
                    predicate.add(cb.equal(root.get("result").as(Enum.class), verifyResultSearchVo.getResult()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
