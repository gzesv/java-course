package edu.hw10.Task2;

import edu.hw10.Task2.annotations.Cache;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxyHandler implements InvocationHandler {
    private Map<String, Object> cache = new HashMap<>();
    private Object target;
    private Path path;

    public CacheProxyHandler(Object target, Path path) {
        this.target = target;
        this.path = path;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation != null) {
            String key = generateKey(method, args);
            return cache.computeIfAbsent(key, i -> invokeAndPersist(method, args));
        } else {
            return method.invoke(target, args);
        }
    }

    private String generateKey(Method method, Object[] args) {
        return method.getName() + Arrays.toString(args);
    }

    private Object invokeAndPersist(Method method, Object[] args) {
        try {
            Object result = method.invoke(target, args);
            persistResult(generateKey(method, args), result);
            return result;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private void persistResult(String key, Object result) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path.resolve(key)))) {
            outputStream.writeObject(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
