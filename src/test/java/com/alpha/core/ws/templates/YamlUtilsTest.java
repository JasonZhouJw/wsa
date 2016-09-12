package com.alpha.core.ws.templates;

import com.alpha.core.ws.entity.Wsdl;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by jzhou237 on 9/12/2016.
 */
public class YamlUtilsTest {

    @Test
    public void testDump() {
        Wsdl wsdl = new Wsdl();
        wsdl.setAddress("address value");
        wsdl.setFacadeClass("facade class value");
        wsdl.setId(1000L);
        YamlUtils.dump("c://tmp/person.yaml", wsdl);
    }

    @Test
    public void testLoad() {
        YamlUtils.load("c://tmp/person.yaml", Wsdl.class, new Consumer<Object>() {

            @Override
            public void accept(Object o) {
                Wsdl wsdl = (Wsdl) o;
                System.out.println(wsdl);
            }
        });
    }

}
