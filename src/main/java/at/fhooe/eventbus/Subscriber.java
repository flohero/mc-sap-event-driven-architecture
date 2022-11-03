package at.fhooe.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class Subscriber {
    private final Object listener;

    public Subscriber(Object listener) {
        this.listener = listener;
    }

    public void invoke(Object arg) {
        Class<?> parameterType = arg.getClass();
        for (Method method : listenerMethods()) {
            if (validMethod(method, parameterType)) {
                continue;
            }
            try {
                method.invoke(listener, arg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<Method> listenerMethods() {
        Class<?> clazz = listener.getClass();
        List<Method> methods = new ArrayList<>();
        for (Method method : clazz.getMethods()) {
            if (method.getAnnotation(Listener.class) != null) {
                methods.add(method);
            }
        }
        return methods;
    }

    private static boolean validMethod(Method method, Class<?> requiredParameterType) {
        var methodArgs = method.getParameterTypes();
        return methodArgs.length != 1 || !methodArgs[0].equals(requiredParameterType);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Subscriber sub) {
            return listener == sub.listener;
        }
        return false;
    }
}
