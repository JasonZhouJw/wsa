package com.alpha.core.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class InterfaceInfo {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @ElementCollection
    private List<InputClass> input = new ArrayList<InputClass>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "output_Id")
    private ClassInfo output;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "wsdl_id")
    private Wsdl wsdl;

    @Column(length = 200)
    private String methodName;

    @Column
    private boolean active = true;

    @SuppressWarnings("rawtypes")
    public InterfaceInfo(Wsdl wsdl, Method method) {
        this.methodName = method.getName();
        Class[] parameterTypes = method.getParameterTypes();
        for (Class parameterType : parameterTypes) {
            input.add(new InputClass(new ClassInfo(parameterType), this));
        }
        output = new ClassInfo(method.getReturnType());
    }

    public String getFullName() {
        return this.wsdl != null ? this.wsdl.getFacadeClass() + "." + this.methodName : this.methodName;
    }

}
