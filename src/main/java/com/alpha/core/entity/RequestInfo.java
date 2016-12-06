package com.alpha.core.entity;

import com.alpha.core.common.exceptions.CommonException;
import com.alpha.core.common.utils.enums.Errors;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import javax.persistence.*;

/**
 * Created by jzhou237 on 9/16/2016.
 */
@Slf4j
@Data
@Entity
public class RequestInfo {

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

    public boolean isSimpleData() {
        // TODO: 9/20/2016  judge the field type is simple data or not
        return false;
    }

    public Object getDataValue() throws CommonException {
        try {
            Yaml yaml = new Yaml();
            return isSimpleData() ? this.value : yaml.loadAs(this.value, Class.forName(this.input.getClassName()));
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(Errors.CLASS_NOT_FOUND, "[" + this.input.getClassName() + "] not found");
        }
    }
}
