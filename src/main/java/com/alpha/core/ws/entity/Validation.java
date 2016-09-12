package com.alpha.core.ws.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 9/12/2016.
 */
@Entity
public class Validation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "output_class_id")
    private ClassInfo output;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<VerifyInfo> verifyList = new ArrayList<VerifyInfo>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassInfo getOutput() {
        return output;
    }

    public void setOutput(ClassInfo output) {
        this.output = output;
    }

    public List<VerifyInfo> getVerifyList() {
        return verifyList;
    }

    public void setVerifyList(List<VerifyInfo> verifyList) {
        this.verifyList = verifyList;
    }
}
