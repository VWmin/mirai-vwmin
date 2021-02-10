package com.vwmin.miraivwmin.event;

import java.lang.reflect.Type;

/**
 * 通过Type获取对象class
 * @author vwmin
 * @version 1.0
 * @date 2021/1/7 21:29
 *
 */
public class TypeUtils {
    private static final String TYPE_NAME_PREFIX = "class ";

    static String getClassName(Type type) {
        if (type==null) {
            return "";
        }
        String className = type.toString();
        if (className.startsWith(TYPE_NAME_PREFIX)) {
            className = className.substring(TYPE_NAME_PREFIX.length());
        }
        return className;
    }

    public static Class<?> getClass(Type type)
            throws ClassNotFoundException {
        String className = getClassName(type);
        return Class.forName(className);
    }
}
