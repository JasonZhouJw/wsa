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

    @ManyToMany
    @ElementCollection
    private List<InputClass> input = new ArrayList<InputClass>();

    @ManyToOne
    @JoinColumn(name = "output_Id")
    private ClassInfo output;

    @JoinColumn(name = "wsdl_id")
    private Wsdl wsdl;

    @Column(length = 200)
    private String methodName;

    public InterfaceInfo() {

    }

    @SuppressWarnings("rawtypes")
    public InterfaceInfo(Wsdl wsdlInfo, Method method) {
        this.methodName = method.getName();
        Class[] parameterTypes = method.getParameterTypes();
        for (Class parameterType : parameterTypes) {
            input.add(new InputClass(new ClassInfo(parameterType),this));
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