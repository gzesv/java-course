package edu.hw10.Task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.nio.file.Path;

@SuppressWarnings("HideUtilityClassConstructor")
public class CacheProxy {
    @SuppressWarnings("unchecked")
    public static <T> T create(T target, Class<T> interfaceClass, Path path) {
        ClassLoader classLoader = interfaceClass.getClassLoader();
        Class<?>[] interfaces = {interfaceClass};
        InvocationHandler handler = new CacheProxyHandler(target, path);

        return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
    }
}
