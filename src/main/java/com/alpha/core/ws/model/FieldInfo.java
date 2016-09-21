/**
 * 平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.alpha.core.ws.model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * @author ZHOUJINGWEI598
 * @version $Id: FieldInfoRepository.java, v 0.1 2016年4月15日 下午4:30:53 ZHOUJINGWEI598 Exp $
 */
public class FieldInfo {

    protected String name;

    @SuppressWarnings("rawtypes")
    protected Class type;

    protected ClassInfo childClazz;

    protected boolean hasChildClass = false;
    ;

    public FieldInfo() {

    }

    public FieldInfo(Field field) {
        this.name = field.getName();
        this.type = field.getType();
        if (this.type.getName().equals("java.util.List")
                || this.type.getName().equals("java.util.Map")) {
            hasChildClass = true;
            ParameterizedType pt = (ParameterizedType) field.getGenericType();
            this.childClazz = new ClassInfo(pt.getActualTypeArguments()[0].getTypeName());
        }
    }

    public boolean hasChildClass() {
        return hasChildClass;
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("rawtypes")
    public Class getType() {
        return type;
    }

    public ClassInfo getChildClazz() {
        return childClazz;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FieldInfo [name=");
        builder.append(name);
        builder.append(", type=");
        builder.append(type);
        builder.append(", childClazz=");
        builder.append(childClazz);
        builder.append("]");
        return builder.toString();
    }

}
