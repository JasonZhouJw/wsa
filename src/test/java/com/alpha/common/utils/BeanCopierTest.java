package com.alpha.common.utils;

import org.junit.Test;

/**
 * Created by jzhou237 on 2016-12-15.
 */
public class BeanCopierTest {

    @Test
    public void test_temp() {
        int balance = 120000;
        int amount = 8200;
        int month = 1;
        do {
            balance = balance + 3800 - amount;
            if (balance <= 0) {
                break;
            }
            month++;
        } while (true);
        System.out.println(month);
    }


}