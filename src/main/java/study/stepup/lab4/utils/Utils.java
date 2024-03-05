package study.stepup.lab4.utils;

import java.lang.reflect.Proxy;

public class Utils {

    public static <T> T log(T tClassElement){

        ClassLoader tClassLoader = tClassElement.getClass().getClassLoader();
        Class[] interfaces = tClassElement.getClass().getInterfaces();
        var logHandler = new LogHandler(tClassElement);

        return (T) Proxy.newProxyInstance(tClassLoader, interfaces, logHandler);
    }

}
