package demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;

public class TestRunner {

    static void start(Class<?> someClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object o = someClass.getDeclaredConstructor().newInstance();

        Method[] declaredMethods = someClass.getDeclaredMethods();
        Method beforeMethod = null;
        Method afterMethod = null;

        List<Method> list = new ArrayList<>();

        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Test.class)) {
                list.add(method);
            } else if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeMethod == null) {
                    beforeMethod = method;
                } else throw new RuntimeException("Multiple methods with BeforeSuite annotation");
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                if (afterMethod == null) {
                    afterMethod = method;
                }else throw new RuntimeException("Multiple methods with AfterSuite annotation");
            }
        }

        list.sort(Comparator.comparingInt(method -> method.getAnnotation(Test.class).priority()));

        if (beforeMethod != null) {
            beforeMethod.invoke(o);
        }

        for (Method method : list) {
            validPriority(method.getAnnotation(Test.class).priority());

            method.invoke(o);
        }

        if (afterMethod != null) {
            afterMethod.invoke(o);
        }
    }

    private static void validPriority(int priority) {
        if (priority < 1 || priority > 10) {
            throw new IllegalArgumentException("Priority must be in range from 1 to 10, your priority is " + priority);
        }
    }
}
