package com.alpha.core.repository.search;

import com.alpha.core.cache.Caches;
import com.alpha.core.repository.search.specifications.Equal;
import com.alpha.core.repository.search.specifications.GreaterThan;
import com.alpha.core.repository.search.specifications.Like;
import com.alpha.core.repository.search.specifications.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 2016-11-28.
 */
@Slf4j
@Getter
@Setter
public class FieldParam {

    private String field;

    private List<Object> values;

    private Operation operation;

    public FieldParam() {

    }

    public FieldParam(String field, Object... values) throws Exception {
        this(field, Operation.EQUAL, values);
    }

    public FieldParam(String field, Operation operation, Object... values) throws Exception {
        this.field = field;
        this.operation = operation;
        this.values = operation.getValues(values);
    }

    public void getSpecification(Consumer<Specification> consumer) {
        Constructor cachedConstructor = (Constructor) Caches.CONSTRUCTOR.get(operation.getClazz().getName());
        if (cachedConstructor == null) {
            Constructor constructors[] = this.operation.getClazz().getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                Class[] types = constructor.getParameterTypes();
                if (types.length == 1 && types[0].getName().equals(this.getClass().getName())) {
                    cachedConstructor = constructor;
                    Caches.CONSTRUCTOR.put(operation.getClazz().getName(), constructor);
                    break;
                }
            }
        }
        try {
            Specification specification = (Specification) operation.getClazz().getConstructor(cachedConstructor.getParameterTypes()).newInstance(this);
            if (specification != null) {
                consumer.accept(specification);
            }
        } catch (InstantiationException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }
    }

    public enum Operation {

        EQUAL("equal", 1, Equal.class),
        GREATER_THAN("greater than", 1, GreaterThan.class),
        NOT_NULL("not null", 0, NotNull.class),
        LIKE("like", 1, Like.class);

        private String type;

        private int parameterNum;

        private Class clazz;

        Operation(String type, int parameterNum, Class clazz) {
            this.clazz = clazz;
            this.parameterNum = parameterNum;
            this.type = type;
        }

        public List<Object> getValues(Object... values) throws Exception {
            List<Object> results = new ArrayList<Object>();
            if (values.length < this.parameterNum) {
                throw new Exception("Miss parameter. Expect parameter size is " + this.parameterNum + ", but actual size is " + values.length);
            }
            for (int i = 0; i < this.parameterNum; i++) {
                results.add(values[i]);
            }
            return results;
        }

        public String getType() {
            return type;
        }

        public Class getClazz() {
            return clazz;
        }
    }


}
