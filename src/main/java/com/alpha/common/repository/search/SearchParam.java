package com.alpha.common.repository.search;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by jzhou237 on 2016-11-28.
 */
@Slf4j
public class SearchParam {

    public static final String AND = "and";

    public static final String OR = "or";

    private List<FieldParam> fields = new ArrayList<FieldParam>();

    private List<String> operations = new ArrayList<String>();

    public SearchParam() {

    }

    public SearchParam(String field, Object value) {
        addField(field, value, fieldParam -> {
            fields.add(fieldParam);
        });
    }

    public SearchParam and(String field, Object value) {
        return this.addParam(field, value, AND);
    }

    public SearchParam or(String field, Object value) {
        return this.addParam(field, value, OR);
    }

    public SearchParam addParam(String field, Object value, String type) {
        addField(field, value, fieldParam -> {
            fields.add(fieldParam);
            operations.add(type);
        });
        return this;
    }

    private void addField(String field, Object value, Consumer<FieldParam> consumer) {
        try {
            consumer.accept(new FieldParam(field, value));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * construct specifications for query
     *
     * @return Specifications
     */
    public Specifications search() {
        if (CollectionUtils.isEmpty(this.fields)) {
            return null;
        }
        Map<Integer, Specification> specificationMap = new TreeMap<Integer, Specification>();
        for (int i = 0; i < this.fields.size(); i++) {
            final Integer index = i;
            this.fields.get(i).getSpecification(specification1 -> {
                specificationMap.put(index, specification1);
            });
        }
        Specifications specifications = null;
        Iterator<Entry<Integer, Specification>> iterator = specificationMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Integer, Specification> entry = iterator.next();
            if (specifications == null) {
                specifications = where(entry.getValue());
            } else {
                switch (operations.get(entry.getKey() - 1)) {
                    case AND: {
                        specifications.and(entry.getValue());
                        break;
                    }
                    case OR: {
                        specifications.or(entry.getValue());
                        break;
                    }
                }
            }
        }
        return specifications;
    }

}
