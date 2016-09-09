/**
 * 平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.alpha.core.ws.entity;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ZHOUJINGWEI598
 * @version $Id: ClassInfo.java, v 0.1 2016年4月15日 下午4:23:04 ZHOUJINGWEI598 Exp $
 */
@Entity
public class ClassInfo {

    @Id
    @GeneratedValue
    private long id;

    @ElementCollection
    @MapKeyColumn(name = "name")
    private Map<String, FieldInfo> fieldMap = new HashMap<String, FieldInfo>();

    @Column(length = 200)
    private String className;

    public ClassInfo() {

    }

    @SuppressWarnings("rawtypes")
    public ClassInfo(String className) {
        this.className = className;
        try {
            Class clazz = Class.forName(this.className);
            this.assembleClass(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("rawtypes")
    public ClassInfo(Class clazz) {
        this.className = clazz.getName();
        this.assembleClass(clazz);
    }

    @SuppressWarnings("rawtypes")
    private void assembleClass(Class clazz) {
        if (isAssembleClass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                FieldInfo fieldInfo = new FieldInfo(field);
                this.fieldMap.put(fieldInfo.getName(), fieldInfo);
            }
        }
    }

    private boolean isAssembleClass() {
        boolean result = true;
        if (this.className.startsWith("java.lang")) {
            result = false;
        }
        return result;
    }

    public Map<String, FieldInfo> getFieldMap() {
        return fieldMap;
    }

    public String getClassName() {
        return className;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFieldMap(Map<String, FieldInfo> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClassInfo [fieldMap=");
        builder.append(fieldMap);
        builder.append(", className=");
        builder.append(className);
        builder.append("]");
        return builder.toString();
    }
}
