package com.alpha.core.ws.entity;

import com.alpha.core.ws.utils.Constants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wsdl implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200)
    private String wsdl;

    @Column(length = 200)
    private String address;

    @Column(length = 200)
    private String facadeClass;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Operation> operationList = new ArrayList<Operation>();

    public String getWsdl() {
        return wsdl;
    }

    public void setWsdl(String wsdl) {
        this.wsdl = wsdl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFacadeClass() {
        return facadeClass;
    }

    public void setFacadeClass(String facadeClass) {
        this.facadeClass = facadeClass;
    }

    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    public void initFacadeClass(String namespace, String className) {
        namespace = namespace.substring(namespace.indexOf(Constants.HTTP_PREFIX)
                        + Constants.HTTP_PREFIX.length(),
                namespace.lastIndexOf(Constants.HTTP_SUFFIX));
        String[] packages = namespace.split(Constants.SEPERATE_DOT);
        StringBuffer packageSb = new StringBuffer();
        for (int i = packages.length - 1; i >= 0; i--) {
            packageSb.append(packages[i]).append(Constants.DOT);
        }
        packageSb.append(className);
        this.facadeClass = packageSb.toString();
    }

    public void addOperation(String operation) {
        if (!this.operationList.contains(operation)) {
            this.operationList.add(new Operation(operation, this));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Wsdl [wsdl=");
        builder.append(wsdl);
        builder.append(", address=");
        builder.append(address);
        builder.append(", facadeClass=");
        builder.append(facadeClass);
        builder.append(", operationList=");
        builder.append(operationList);
        builder.append("]");
        return builder.toString();
    }

}
