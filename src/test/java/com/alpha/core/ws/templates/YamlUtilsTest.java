package com.alpha.core.ws.templates;

import com.alpha.core.ws.entity.Wsdl;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;

/**
 * Created by jzhou237 on 9/12/2016.
 */
public class YamlUtilsTest {

    @Test
    public void tttt() {
        for (int i = 2; i < 100; i++) {
            System.out.println(i + " Teams: " + getTeam(i));
        }
    }

    private int getTeam(int num) {
        if (num == 2) {
            return 1;
        }
        int number = (num - num % 2) / 2;
        return number + getTeam(number + num % 2);
    }

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
        Object[] data = new Object[10];
        data[0] = "Number1";
        Wsdl wsdl = new Wsdl();
        wsdl.setAddress("address value");
        data[1] = wsdl;
//        YamlUtils.dump("c://tmp/person.yaml", data);
        System.out.println(data.getClass());
        System.out.println(data.getClass().getTypeName());
    }

    @Test
    public void testLoad() throws FileNotFoundException {
//        YamlUtils.load("c://tmp/person.yaml", Wsdl.class, new Consumer<Object>() {
//
//            @Override
//            public void accept(Object o) {
//                Wsdl wsdl = (Wsdl) o;
//                System.out.println(wsdl);
//            }
//        });

//        YamlStream stream=Yaml.loadStream(new File("c://tmp/person.yaml"));
//        Iterator iter=stream.iterator();
//        while(iter.hasNext()){
//            Object obj=iter.next();
//            System.out.println(obj);
//            System.out.println(obj.getClass().getDescription());
//        }
//
//        YamlUtils.load("c://tmp/person.yaml", ArrayList.class, new Consumer<Object>() {
//
//            @Override
//            public void accept(Object o) {
////                List wsdl = (List) o;
//                System.out.println(o);
//            }
//        });
    }

}
