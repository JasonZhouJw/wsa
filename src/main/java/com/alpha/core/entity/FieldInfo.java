package com.alpha.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;


@Data
@NoArgsConstructor
@Entity
public class FieldInfo {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 200)
    private String name;

    @Column(length = 200)
    private String type;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "class_id")
    private ClassInfo childClazz;

    private boolean hasChildClass = false;

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


}
