package com.alpha.common.page;


import com.alpha.common.view.Params;
import com.alpha.common.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.alpha.common.page.CurrentPage.FIRST_PAGE;
import static com.alpha.common.view.PropertyResources.RESULT_MESSAGES_FULL_NAME;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PageView extends ModelAndView implements View<Integer> {

    public static final String PAGE_PARAM_NAME = "page";
    private final CurrentPage currentPage;

    @Value("${application.perPageLimit}")
    private int perPageLimit;

    @Value("${pagination.totalPage}")
    private String totalPageMessage;

    public PageView(
            @Value("${pagination.currentPage}") String currentPageMessage,
            @Autowired CurrentPage currentPage) {
        this.currentPage = currentPage;
        displayCurrentPage(currentPageMessage);
        displayPreviousPage();
    }

    private void displayPreviousPage() {
        if (currentPage.number() != FIRST_PAGE)
            displayPreviousPageUrl();
    }

    private void displayPreviousPageUrl() {
        addObject("previousPageUrl", pageUrl(currentPage.number() - 1));
    }

    private void displayCurrentPage(String currentPageMessage) {
        addObject("currentPage", format(currentPageMessage, currentPage.number()));
    }

    @Override
    public void display(Integer totalPageCount) {
        addObject("totalPage", format(this.totalPageMessage, totalPageCount));
        if (currentPage.number() < totalPageCount) {
            displayNextPageUrl();
        }
    }

    private ModelAndView displayNextPageUrl() {
        return addObject("nextPageUrl", pageUrl(currentPage.number() + 1));
    }

    private String pageUrl(int pageNumber) {
        Params page = new Params();
        page.add(PAGE_PARAM_NAME, valueOf(pageNumber));
        return page.getQuery();
    }

    public Pageable create() {
        return new PageRequest(currentPage.number() - 1, perPageLimit);
    }

    public Pageable create(Sort sort) {
        return new PageRequest(currentPage.number() - 1, perPageLimit, sort);
    }
}
