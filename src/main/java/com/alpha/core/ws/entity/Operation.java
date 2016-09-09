package com.alpha.core.ws.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jzhou237 on 9/9/2016.
 */
@Entity
public class Operation implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200)
    private String method;

    @ManyToOne
    @JoinColumn(name="wsdl_id")
    private Wsdl wsdl;

    public Operation(){

    }

    public Operation(String method, Wsdl wsdl){
        this.method=method;
        this.wsdl=wsdl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Wsdl getWsdl() {
        return wsdl;
    }

    public void setWsdl(Wsdl wsdl) {
        this.wsdl = wsdl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Operation operation = (Operation) o;
        return method != null ? method.equals(operation.method) : operation.method == null;

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
