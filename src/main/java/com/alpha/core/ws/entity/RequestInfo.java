package com.alpha.core.ws.entity;

import com.alpha.core.ws.exception.CommonException;
import com.alpha.core.ws.utils.ILog;
import com.alpha.core.ws.utils.YamlUtils;
import com.alpha.core.ws.utils.enums.Errors;

import javax.persistence.*;

/**
 * Created by jzhou237 on 9/16/2016.
 */
@Entity
public class RequestInfo implements ILog {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * YAML data
     */
    @Column(length = 4000)
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

    public boolean isSimpleData() {
        // TODO: 9/20/2016  judge the field type is simple data or not
        return false;
    }

    public Object getDataValue() throws CommonException {
        try {
            return isSimpleData() ? this.value : YamlUtils.load(this.value, Class.forName(this.input.getClassName()));
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new CommonException(Errors.CLASS_NOT_FOUND, "[" + this.input.getClassName() + "] not found");
        }
    }
}
