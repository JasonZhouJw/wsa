package com.alpha.web.model.common;

import org.springframework.data.domain.Page;

/**
 * Created by jzhou237 on 2016-11-29.
 */
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

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
