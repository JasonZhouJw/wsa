package com.alpha.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by jzhou237 on 9/9/2016.
 */
@Data
@NoArgsConstructor
@Entity
public class InputClass {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "interface_id")
    private InterfaceInfo interfaceInfo;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassInfo classInfo;

    public InputClass(ClassInfo classinfo, InterfaceInfo interfaceInfo) {
        this.classInfo = classinfo;
        this.interfaceInfo = interfaceInfo;
    }

}
