package com.alpha.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@NoArgsConstructor
public class ClassInfo {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    @ElementCollection
    @MapKeyColumn(name = "name")
    private Map<String, FieldInfo> fieldMap = new HashMap<String, FieldInfo>();

    @Column(length = 200)
    private String className;

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

}
