package com.alpha.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jzhou237 on 9/9/2016.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Operation implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200)
    private String method;

    @ManyToOne
    @JoinColumn(name = "wsdl_id")
    private Wsdl wsdl;


    public Operation(String method, Wsdl wsdl) {
        this.method = method;
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

}
