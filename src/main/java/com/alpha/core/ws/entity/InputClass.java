package com.alpha.core.ws.entity;

import javax.persistence.*;

/**
 * Created by jzhou237 on 9/9/2016.
 */
@Entity
public class InputClass {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="interface_id")
    private InterfaceInfo interfaceInfo;

    @ManyToOne
    @JoinColumn(name="class_id")
    private ClassInfo classInfo;

    public InputClass(){

    }

    public InputClass(ClassInfo classinfo, InterfaceInfo interfaceInfo){
        this.classInfo=classinfo;
        this.interfaceInfo=interfaceInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public InterfaceInfo getInterfaceInfo() {
        return interfaceInfo;
    }

    public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
        this.interfaceInfo = interfaceInfo;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }
}
