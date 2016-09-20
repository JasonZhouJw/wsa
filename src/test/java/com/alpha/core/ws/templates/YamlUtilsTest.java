package com.alpha.core.ws.templates;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 9/12/2016.
 */
public class YamlUtilsTest {

    @Test
    public void test_temp() throws Exception {
        YamlUtilsTest obj = new YamlUtilsTest();
        Method method = YamlUtilsTest.class.getMethod("testDump");
        Object returnObj = method.invoke(obj);
        System.out.println(returnObj);

        method = YamlUtilsTest.class.getMethod("getString");
        returnObj = method.invoke(obj);
        System.out.println(returnObj);
    }

    public String getString() {
        return null;
    }

    @Test
    public void testDump() {
//        Wsdl wsdl = new Wsdl();
//        wsdl.setAddress("address value");
//        wsdl.setFacadeClass("facade class value");
//        wsdl.setId(1000L);
//        List<Operation> operationList=new ArrayList<Operation>();
//        operationList.add(new Operation("method1",wsdl));
//        operationList.add(new Operation("method2",wsdl));
//        wsdl.setOperationList(operationList);
//        YamlUtils.dump("c://tmp/person.yaml", wsdl);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("function", "string");
        mapData.put("field", "name");
        mapData.put("operation", "eq");
        mapData.put("expect", "Test");
        data.add(mapData);
        mapData = new HashMap<String, Object>();
        mapData.put("function", "int");
        mapData.put("field", "age");
        mapData.put("operation", "eq");
        mapData.put("expect", 18);
        data.add(mapData);
        YamlUtils.dump("c://tmp/person.yaml", data);
    }

    @Test
    public void testLoad() {
//        YamlUtils.load("c://tmp/person.yaml", Wsdl.class, new Consumer<Object>() {
//
//            @Override
//            public void accept(Object o) {
//                Wsdl wsdl = (Wsdl) o;
//                System.out.println(wsdl);
//            }
//        });


        YamlUtils.load("c://tmp/person.yaml", ArrayList.class, new Consumer<Object>() {

            @Override
            public void accept(Object o) {
                List wsdl = (List) o;
                System.out.println(wsdl);
            }
        });
    }

}
