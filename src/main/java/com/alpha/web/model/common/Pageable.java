package com.alpha.web.model.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

/**
 * Created by jzhou237 on 2016-11-29.
 */
@Getter
@Setter
@ToString
public class Pageable {

    private int current;

    private int next;

    private int previous;

    private long totalNum;

    private int totalPage;

    private int size;

    public static Pageable toPageable(Page page) {
        Pageable pageable = new Pageable();
        pageable.setCurrent(page.getNumber());
        pageable.setNext(page.nextPageable() == null ? -1 : page.nextPageable().getPageNumber());
        pageable.setPrevious(page.previousPageable() == null ? -1 : page.previousPageable().getPageNumber());
        pageable.setTotalNum(page.getTotalElements());
        pageable.setTotalPage(page.getTotalPages());
        pageable.setSize(page.getSize());
        return pageable;
    }

}
