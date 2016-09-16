package com.alpha.core.ws.entity;

import javax.persistence.*;

/**
 * Created by jzhou237 on 9/16/2016.
 */
@Entity
public class RequestInfo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200)
    private String value;

    @ManyToOne
    @JoinColumn(name = "input_class_id")
    private ClassInfo input;

    @ManyToOne
    @JoinColumn(name = "test_case_id")
    private TestCase testCase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ClassInfo getInput() {
        return input;
    }

    public void setInput(ClassInfo input) {
        this.input = input;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }
}
