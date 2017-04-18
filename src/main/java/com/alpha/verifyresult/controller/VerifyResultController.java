package com.alpha.verifyresult.controller;

import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.common.page.PageView;
import com.alpha.verifyresult.domain.VerifyResultImpl;
import com.alpha.verifyresult.entities.VerifyResult;
import com.alpha.verifyresult.model.VerifyResultSearchVo;
import com.alpha.verifyresult.view.VerifyResultIndexView;
import com.alpha.verifyresult.view.VerifyResultUpdateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.function.Consumer;

import static com.alpha.common.controller.Urls.*;

/**
 * Created by jzhou237 on 2016-11-29.
 */
@Controller
public class VerifyResultController {

    @Autowired
    private VerifyResultIndexView verifyResultIndexView;

    @Autowired
    private VerifyResultUpdateView verifyResultUpdateView;

    @Autowired
    private PageView pageView;

    @Autowired
    private VerifyResultImpl verifyResult;

    @GetMapping(VERIFY_RESULT_INDEX)
    public ModelAndView index() {
        this.verifyResult.findAll(pageView.create(new Sort(Sort.Direction.DESC, "id")), new Consumer<Page<VerifyResult>>() {
            @Override
            public void accept(Page<VerifyResult> verifyResults) {
                pageView.display(verifyResults.getTotalPages());
                verifyResultIndexView.setVerifyResult(verifyResults.getContent());
            }
        });
        return this.verifyResultIndexView.Combine(pageView);
    }

    @GetMapping(VERIFY_RESULT_TO_UPDATE + "/{id}")
    public ModelAndView toUpdate(@PathVariable("id") Long id) {
        try {
            this.verifyResultUpdateView.getResultHandler().successNoMsg(this.verifyResult.findOne(id));
        } catch (DataNotFoundException e) {
            this.verifyResultUpdateView.getResultHandler().fail(null, e.getMessage());
        }
        return verifyResultUpdateView;
    }

    @GetMapping(VERIFY_RESULT_SEARCH)
    public ModelAndView search(VerifyResultSearchVo verifyResultVo) {
        this.verifyResult.search(verifyResultVo, pageView.create(new Sort(Sort.Direction.DESC, "id")), new Consumer<Page<VerifyResult>>() {
            @Override
            public void accept(Page<VerifyResult> verifyResults) {
                pageView.display(verifyResults.getTotalPages());
                verifyResultIndexView.setSearchParam(verifyResultVo);
                verifyResultIndexView.setVerifyResult(verifyResults.getContent());
            }
        });
        return verifyResultIndexView;
    }
}
