package com.alpha.core.wsdl2java.executors;

import com.alpha.core.loader.ClassCache;
import com.alpha.core.server.entity.User;
import com.alpha.core.wsdl2java.entities.ServiceMethod;
import com.alpha.core.wsdl2java.entities.Services;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jzhou237 on 2016-12-09.
 */
@Slf4j
public class ServiceExecutor {

    public void execute(Services services, String servicesMethod) {
        ServiceMethod serviceMethod = services.getMethodArgumentsMap().get(servicesMethod);
        if (serviceMethod == null) {
            log.error("can not find the method [" + servicesMethod + "] in " + services.getInterfaceClass());
            return;
        }
        try {
            Class clazz = ClassCache.getInstance().getClass(services.getServicesName());
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getParameterCount() == 0 && method.getReturnType().getName().equals(services.getInterfaceClass())) {
                    Object serviceFactoryObj = clazz.newInstance();
                    Object serviceObj = method.invoke(serviceFactoryObj);
                    this.send(serviceObj, serviceMethod);
                }
            }
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        } catch (InstantiationException e) {
            log.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void send(Object serviceObj, ServiceMethod serviceMethod) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = serviceObj.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(serviceMethod.getMethod())) {
                // TODO: 2016-12-09 need to refactor. should construct the request parameters and verify the response
                User user = new User();
                user.setName("name");
                user.setAge(1);
                User returnObj = (User) method.invoke(serviceObj, user);
                System.out.print(returnObj);
            }
        }
    }

}
