package com.alpha.core.ws.templates;

import com.alpha.core.ws.utils.ILog;
import org.ho.yaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 9/12/2016.
 */
public class YamlUtils implements ILog {

    public static void dump(String fileName, Object target) {
        File file = new File(fileName);
        try {
            Yaml.dump(target, file, true);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static void load(String fileName, Class clazz, Consumer<Object> consumer) {
        File file = new File(fileName);
        try {
            Object obj = Yaml.loadType(file, clazz);
            consumer.accept(obj);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
