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
public class Like extends BaseSpecification implements Specification {
    public Like(FieldParam fieldParam) {
        super(fieldParam);
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
        return cb.like(this.getPath(root), (String) this.fieldParam.getValues().get(0));
    }
}
