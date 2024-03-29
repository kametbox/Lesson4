package study.stepup.lab4.checks;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class LogTransBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(LogTransformation.class)) {
            map.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = map.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        String log = formatter.format(new Date()) + '\n';
                        log+= beanClass.getName()+ '\n';
                        log+= "IN : ";
                        log+= Arrays.toString(args);
                        log+= " " + '\n';
                        Object result = method.invoke(bean, args);
                        log+= "OUT: " + result.toString() + '\n';
                        log+= "\n";

                        try (FileWriter fw = new FileWriter(bean.getClass().getAnnotation(LogTransformation.class).fileName(), true);
                             BufferedWriter bw = new BufferedWriter(fw)) {
                            bw.write(log);
                        }

                        return result;
                    });
        }
        return bean;
    }
}
