/**
 * 平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.alpha.core.ws.entity;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 *
 * @author ZHOUJINGWEI598
 * @version $Id: FieldInfoRepository.java, v 0.1 2016年4月15日 下午4:30:53 ZHOUJINGWEI598 Exp $
 */
@Entity
public class FieldInfo {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 200)
    private String name;

    @Column(length=200)
    private String type;

    @OneToOne
    @JoinColumn(name = "class_id")
    private ClassInfo childClazz;

    private boolean hasChildClass = false;

    public FieldInfo() {

    }

    public FieldInfo(Field field) {
        this.name = field.getName();
        this.type = field.getType().getTypeName();
        if (this.type.equals("java.util.List")
                || this.type.equals("java.util.Map")) {
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
    public String getType() {
        return type;
    }

    public ClassInfo getChildClazz() {
        return childClazz;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setChildClazz(ClassInfo childClazz) {
        this.childClazz = childClazz;
    }

    public boolean isHasChildClass() {
        return hasChildClass;
    }

    public void setHasChildClass(boolean hasChildClass) {
        this.hasChildClass = hasChildClass;
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
