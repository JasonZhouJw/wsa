package com.alpha.web.services;

import com.alpha.core.ws.entity.VerifyResult;
import com.alpha.core.ws.repository.search.SearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by jzhou237 on 2016-11-25.
 */
public interface IVerifyResultService {

    Page<VerifyResult> search(SearchParam searchParam, PageRequest pageRequest);

    List<VerifyResult> search(SearchParam searchParam);
}
