package com.vwmin.miraivwmin.util;

import java.lang.reflect.Method;

/**
 * @author vwmin
 * @version 1.0
 * @date 2020/4/6 12:40
 */
public class ExceptionUtil {
    private ExceptionUtil(){}

    public static String classWarning(Class<?> clazz, String message, Object... args){
        return String.format(message + "\n\tfor class: " + clazz.getSimpleName(), args);
    }

    public static RuntimeException classError(Class<?> clazz, String message, Object... args){
        message = String.format(message, args);
        return new IllegalArgumentException(
                message
                        + "\n\tfor class: "
                        + clazz.getSimpleName()
        );
    }

    public static RuntimeException methodError(Method method, String message, Object... args) {
        message = String.format(message, args);
        return new IllegalArgumentException(
                message
                        + "\n    for method "
                        + method.getDeclaringClass().getSimpleName()
                        + "."
                        + method.getName()
        );
    }

    public static RuntimeException parameterError(Method method, int index, String message, Object... args) {
        return methodError(method, message + " (parameter #" + (index + 1) + ")", args);
    }

    public static void notNull(Method method, Object object, String message, Object... args){
        if (object == null){
            throw methodError(method, message, args);
        }
    }

    public static void notEmpty(CharSequence cs, String message){
        if (cs == null || cs.length() == 0){
            throw new IllegalArgumentException(message);
        }
    }
}
