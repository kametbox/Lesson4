package study.stepup.lab4.utils;

import study.stepup.lab4.checks.LogTransformation;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

class LogHandler implements InvocationHandler {
    private Object object;
    public LogHandler(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method tmp = object.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
        LogTransformation logTransformation = tmp.getAnnotation(LogTransformation.class);

        Object retObj = method.invoke(object, args);
        if (tmp.isAnnotationPresent(LogTransformation.class)) {

            try (FileWriter writer = new FileWriter(logTransformation.fileName(), true)) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                AtomicReference<String> log = new AtomicReference<>(formatter.format(date) + '\n');
                log.set(log + object.getClass().getName() + "." + method.getName() + '\n');
                log.set(log + "IN : ");
                Arrays.stream(args).forEach(s -> log.set(log + s.toString()));
                log.set(log + " " + '\n');
                log.set(log + "OUT: " + retObj.toString() + '\n');
                log.set(log + "\n");
                writer.write(log.get());
                writer.flush();
            } catch (IOException ex) {

                System.out.println("Err!: "+ ex.getMessage());
            }
        }

        return retObj;
    }


}

