package com.alpha.verifyresult.domain;

import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.verifyresult.entities.VerifyResult;
import com.alpha.verifyresult.model.VerifyResultSearchVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 2017-04-06.
 */
public interface IVerifyResult {

    /**
     * save the result after executing test case.
     * <p>
     * <b>Transaction type is nested.</b>
     *
     * @param resultMap
     */
    void saveResult(Map<String, VerifyResult> resultMap);

    void findAll(Pageable pageable, Consumer<Page<VerifyResult>> consumer);

    VerifyResult findOne(Long id) throws DataNotFoundException;

    void search(VerifyResultSearchVo verifyResultVo, Pageable pageable, Consumer<Page<VerifyResult>> consumer);
}
