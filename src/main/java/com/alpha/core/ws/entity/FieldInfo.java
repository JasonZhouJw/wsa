package com.alpha.core.ws.entity;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;


@Entity
public class FieldInfo {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 200)
    private String name;

    @Column(length = 200)
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

    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("rawtypes")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ClassInfo getChildClazz() {
        return childClazz;
    }

    public void setChildClazz(ClassInfo childClazz) {
        this.childClazz = childClazz;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
