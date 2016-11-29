package com.alpha.web.services.impl;

import com.alpha.core.ws.entity.VerifyResult;
import com.alpha.core.ws.repository.VerifyResultRepository;
import com.alpha.core.ws.repository.search.SearchParam;
import com.alpha.web.services.IVerifyResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jzhou237 on 2016-11-25.
 */
@Service
public class VerifyResultServiceImpl implements IVerifyResultService {

    @Autowired
    private VerifyResultRepository verifyResultRepository;

    @Override
    public Page<VerifyResult> search(SearchParam searchParam, PageRequest pageRequest) {
        return this.verifyResultRepository.findAll(searchParam.search(), pageRequest);
    }

    @Override
    public List<VerifyResult> search(SearchParam searchParam) {
        return this.verifyResultRepository.findAll(searchParam.search());
    }
}
