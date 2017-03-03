package com.alpha.common.view;

import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Stream;

/**
 * Created by jzhou237 on 2017-03-03.
 */
@FunctionalInterface
public interface ModelAndViewCombiner {

    default ModelAndView Combine(ModelAndView... toBeMerged) {
        Stream.of(toBeMerged).map(ModelAndView::getModelMap).forEach(original()::addAllObjects);
        return original();
    }

    ModelAndView original();
}
