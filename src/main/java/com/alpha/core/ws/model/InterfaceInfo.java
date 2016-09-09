package com.alpha.core.ws.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class InterfaceInfo {

    private List<ClassInfo> input = new ArrayList<ClassInfo>();

    private ClassInfo output;

    private WsdlInfo wsdlInfo;

    private String methodName;

    public InterfaceInfo() {

    }

    @SuppressWarnings("rawtypes")
    public InterfaceInfo(WsdlInfo wsdlInfo, Method method) {
        this.methodName = method.getName();
        Class[] parameterTypes = method.getParameterTypes();
        for (Class parameterType : parameterTypes) {
            ClassInfo classInfo = new ClassInfo(parameterType);
            input.add(classInfo);
        }
        output = new ClassInfo(method.getReturnType());
    }

    public List<ClassInfo> getInput() {
        return input;
    }

    public void setInput(List<ClassInfo> input) {
        this.input = input;
    }

    public ClassInfo getOutput() {
        return output;
    }

    public void setOutput(ClassInfo output) {
        this.output = output;
    }

    public WsdlInfo getWsdlInfo() {
        return wsdlInfo;
    }

    public void setWsdlInfo(WsdlInfo wsdlInfo) {
        this.wsdlInfo = wsdlInfo;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WebserviceClassInfo [input=");
        builder.append(input);
        builder.append(", output=");
        builder.append(output);
        builder.append(", wsdlInfo=");
        builder.append(wsdlInfo);
        builder.append(", methodName=");
        builder.append(methodName);
        builder.append("]");
        return builder.toString();
    }
}
