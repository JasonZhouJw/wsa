package com.alpha.core.repository.search;

import com.alpha.core.common.utils.Constants;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 * Created by jzhou237 on 2016-11-28.
 */
public abstract class BaseSpecification implements Specification {
    protected FieldParam fieldParam;

    public BaseSpecification(FieldParam fieldParam) {
        this.fieldParam = fieldParam;
    }

    public Path getPath(Root root) {
        Path path = null;
        if (fieldParam.getField().indexOf(Constants.DOT) >= 0) {
            String[] fields = fieldParam.getField().split(Constants.SEPERATE_DOT);
            Join join = null;
            for (int i = 0; i < fields.length - 1; i++) {
                if (join == null) {
                    join = root.join(fields[i]);
                } else {
                    join = join.join(fields[i]);
                }
            }
            path = join.get(fields[fields.length - 1]);
        } else {
            path = root.get(fieldParam.getField());
        }
        return path;
    }
}
