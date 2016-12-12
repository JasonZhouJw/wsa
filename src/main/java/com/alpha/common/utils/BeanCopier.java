package com.alpha.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-10-07.
 */
@Slf4j
public class BeanCopier {

    public static Object copyBean(Object original, Class targetClazz) {
        Object targetObj = null;
        try {
            targetObj = targetClazz.newInstance();
            BeanUtils.copyProperties(targetObj, original);
        } catch (InstantiationException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
        return targetObj;
    }

    public static List copyBean(List original, Class targetClazz) {
        List targetList = new ArrayList();
        if (original != null) {
            original.forEach(element -> {
                targetList.add(copyBean(element, targetClazz));
            });
        }
        return targetList;
    }

    public static void copyBean(Object original, Object target) {
        try {
            BeanUtils.copyProperties(target, original);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
    }
}
