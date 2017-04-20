package org.springframework.core.convert.support;

import com.alpha.common.enums.IIdentifierEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jzhou237 on 2017-04-19.
 */
@Slf4j
final class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnum(ConversionUtils.getEnumType(targetType));
    }


    private class StringToEnum<T extends Enum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            if (source.length() == 0) {
                // It's an empty enum identifier: reset the enum value to null.
                return null;
            }
            for (Class interfaceClazz : this.enumType.getInterfaces()) {
                if (interfaceClazz.getName().equals(IIdentifierEnum.class.getName())) {
                    for (T element : this.enumType.getEnumConstants()) {
                        try {
                            Method method = element.getClass().getMethod("getIdentifier");
                            String identifier = (String) method.invoke(element);
                            if (StringUtils.equals(identifier, source)) {
                                return element;
                            }
                        } catch (NoSuchMethodException e) {
                            log.error(e.getMessage(), e);
                        } catch (IllegalAccessException e) {
                            log.error(e.getMessage(), e);
                        } catch (InvocationTargetException e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                }
            }
            return (T) Enum.valueOf(this.enumType, source.trim());
        }
    }

}
