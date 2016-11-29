package com.alpha.core.ws.repository.search.specifications;

import com.alpha.core.ws.repository.search.BaseSpecification;
import com.alpha.core.ws.repository.search.FieldParam;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by jzhou237 on 2016-11-29.
 */
public class GreaterThan extends BaseSpecification implements Specification {

    public GreaterThan(FieldParam fieldParam) {
        super(fieldParam);
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
        Object value = fieldParam.getValues().get(0);
        if (value instanceof Number) {
            return cb.gt(this.getPath(root), (Number) value);
        }
        return cb.greaterThan(this.getPath(root), (String) fieldParam.getValues().get(0));
    }
}
