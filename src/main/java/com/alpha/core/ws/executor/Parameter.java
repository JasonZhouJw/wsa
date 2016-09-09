package com.alpha.core.ws.executor;

/**
 * Created by jzhou237 on 9/9/2016.
 */
public class Parameter {

    private static class Inner {
        public static final Parameter instance = new Parameter();
    }

    private Parameter() {

    }

    public static Parameter getInstance() {
        return Inner.instance;
    }

}
