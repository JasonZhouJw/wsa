package com.alpha.web.services;

import com.alpha.core.ws.entity.VerifyResult;

import java.util.List;

/**
 * Created by jzhou237 on 2016-11-25.
 */
public interface IVerifyResultService {

    List<VerifyResult> search(VerifyResult verifyResult);

}
