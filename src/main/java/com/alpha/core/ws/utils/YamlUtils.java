package com.alpha.core.ws.utils;

import com.alpha.core.ws.exception.CommonException;
import com.alpha.core.ws.utils.enums.Errors;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 9/12/2016.
 */
public class YamlUtils implements ILog {

    public static void dump(String fileName, Object target) throws CommonException {
        try {
            Yaml yaml = new Yaml();
            FileWriter fw = new FileWriter(new File(fileName));
            yaml.dump(target);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new CommonException(Errors.FILE_NOT_FOUND, e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new CommonException(Errors.IO_EXCEPTION, e);
        }
    }

    public static void load(String fileName, Consumer<Object> consumer) {
        try {
            File file = new File(fileName);
            Yaml yaml = new Yaml();
            Object obj = yaml.load(new FileReader(new File(fileName)));
            consumer.accept(obj);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
