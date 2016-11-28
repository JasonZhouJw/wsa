package com.alpha.web.services.impl;

import com.alpha.core.ws.entity.VerifyResult;
import com.alpha.core.ws.repository.VerifyResultRepository;
import com.alpha.core.ws.repository.search.SearchParam;
import com.alpha.web.services.IVerifyResultService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<VerifyResult> search(VerifyResult verifyResult) {
        SearchParam searchParam = new SearchParam("testCase.interfaceInfo.id", verifyResult.getTestCase().getInterfaceInfo().getId()).and("interfaceInfo.methodName", verifyResult.getInterfaceInfo().getMethodName());
        return this.verifyResultRepository.findAll(searchParam.search());
    }
}
