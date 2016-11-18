package com.alpha.core.ws.entity;


import javax.persistence.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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

    public InterfaceInfo() {

    }

    @SuppressWarnings("rawtypes")
    public InterfaceInfo(Wsdl wsdl, Method method) {
        this.methodName = method.getName();
        Class[] parameterTypes = method.getParameterTypes();
        for (Class parameterType : parameterTypes) {
            input.add(new InputClass(new ClassInfo(parameterType), this));
        }
        output = new ClassInfo(method.getReturnType());
    }

    public List<InputClass> getInput() {
        return input;
    }

    public void setInput(List<InputClass> input) {
        this.input = input;
    }

    public ClassInfo getOutput() {
        return output;
    }

    public void setOutput(ClassInfo output) {
        this.output = output;
    }

    public Wsdl getWsdl() {
        return wsdl;
    }

    public void setWsdl(Wsdl wsdl) {
        this.wsdl = wsdl;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public String getFullName() {
        return this.wsdl != null ? this.wsdl.getFacadeClass() + "." + this.methodName : this.methodName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WebserviceClassInfo [input=");
        builder.append(input);
        builder.append(", output=");
        builder.append(output);
        builder.append(", wsdl=");
        builder.append(wsdl);
        builder.append(", methodName=");
        builder.append(methodName);
        builder.append("]");
        return builder.toString();
    }
}
