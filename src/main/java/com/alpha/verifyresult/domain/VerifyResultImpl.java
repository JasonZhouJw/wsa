package com.alpha.verifyresult.domain;

import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.verifyresult.entities.VerifyResult;
import com.alpha.verifyresult.repository.VerifyResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public void findAll(VerifyResult verifyResult, Pageable pageable, Consumer<Page<VerifyResult>> consumer) {
        Page<VerifyResult> verifyResultPage = this.verifyResultRepository.findAll(verifyResult.getExample(), pageable);
        if (verifyResultPage != null) {
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
}
