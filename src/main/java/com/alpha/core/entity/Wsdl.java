package com.alpha.core.entity;

import com.alpha.core.common.utils.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Wsdl implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private boolean active = true;

    @Column(length = 200)
    private String wsdl;

    @Column(length = 200)
    private String address;

    @Column(length = 200)
    private String facadeClass;

    @ManyToOne(cascade = CascadeType.DETACH, optional = false)
    @JoinColumn(name = "service_info_id")
    private ServicesInfo servicesInfo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Operation> operationList = new ArrayList<Operation>();

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

}
