package com.alpha.web.verifyresult.domain;

import com.alpha.core.entity.VerifyResult;
import com.alpha.core.repository.VerifyResultRepository;
import com.alpha.core.repository.search.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jzhou237 on 2016-11-25.
 */
@Service
public class VerifyResults {

    @Autowired
    private VerifyResultRepository verifyResultRepository;

    public Page<VerifyResult> search(SearchParam searchParam, PageRequest pageRequest) {
        return this.verifyResultRepository.findAll(searchParam.search(), pageRequest);
    }

    public List<VerifyResult> search(SearchParam searchParam) {
        return this.verifyResultRepository.findAll(searchParam.search());
    }
}
