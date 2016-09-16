package com.alpha.core.ws.validation;

import com.alpha.core.ws.entity.VerifyResult;
import com.alpha.core.ws.exception.ValidationException;
import com.alpha.core.ws.utils.ILog;
import com.sun.javafx.binding.StringFormatter;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by jzhou237 on 9/14/2016.
 */
public class ValidationExecutor implements ILog {

    public static void execute(Map<String, VerifyResult> resultMap, Object actualObj, Validation validation) throws ValidationException {
        validation.getVerifyList().forEach(verifyInfo -> {
            try {
                Field field = actualObj.getClass().getField(verifyInfo.getFiledInfo().getName());
                field.setAccessible(true);
                Object fieldValue = field.get(actualObj);
                resultMap.get(verifyInfo.getFiledInfo().getName()).setActualValue(fieldValue.toString());
                boolean result = verifyInfo.getOperationType().compare(verifyInfo.getConverterType().convert(fieldValue), verifyInfo.getExpectValue());
                resultMap.get(verifyInfo.getFiledInfo().getName()).setResult(result);
                if (!result) {
                    resultMap.get(verifyInfo.getFiledInfo().getName()).setMessage(StringFormatter.format(verifyInfo.getOperationType().getMessageTemplate(),
                            verifyInfo.getFiledInfo().getName(), verifyInfo.getExpectValue(), fieldValue.toString()).getValue());
                }
            } catch (NoSuchFieldException e) {
                LOGGER.error(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        });
    }
}
