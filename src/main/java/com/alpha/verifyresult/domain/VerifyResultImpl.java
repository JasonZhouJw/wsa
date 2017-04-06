package com.alpha.verifyresult.domain;

import com.alpha.verifyresult.entities.VerifyResult;
import com.alpha.verifyresult.repository.VerifyResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

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

}
