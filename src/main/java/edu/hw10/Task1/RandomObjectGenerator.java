package edu.hw10.Task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class RandomObjectGenerator {
    private static final int STRING_LENGTH = 15;
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public Object nextObject(Class<?> targetClass) {
        Constructor<?>[] constructors = targetClass.getConstructors();
        Object instance = generateInstance(constructors);

        if (instance == null) {
            constructors = targetClass.getDeclaredConstructors();
            return generateInstance(constructors);
        }

        return instance;
    }

    public Object nextObject(Class<?> targetClass, String targetFactory) {
        Method[] factories = Arrays.stream(targetClass.getMethods())
            .filter(method -> method.getName().equals(targetFactory))
            .toArray(Method[]::new);
        return generateFactoryInstance(factories);
    }

    private Object generateFactoryInstance(Method[] factories) {
        Object instance = null;
        int factoryNumber = 0;

        while (instance == null && factoryNumber < factories.length) {
            Method currentFactory = factories[factoryNumber];
            Object[] randomParams = generateRandomParameters(currentFactory.getParameters());
            try {
                instance = currentFactory.invoke(null, randomParams);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return instance;
    }

    private Object generateInstance(Constructor<?>[] constructors) {
        Object instance = null;
        int constructorNumber = 0;
        while (instance == null && constructorNumber < constructors.length) {
            Constructor<?> currentConstructor = constructors[constructorNumber];
            Object[] randomParams = generateRandomParameters(currentConstructor.getParameters());
            try {
                if (Modifier.isPrivate(currentConstructor.getModifiers())) {
                    currentConstructor.setAccessible(true);
                    instance = currentConstructor.newInstance(randomParams);
                    currentConstructor.setAccessible(false);
                } else {
                    instance = currentConstructor.newInstance(randomParams);
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    private Object[] generateRandomParameters(Parameter[] parameters) {
        Object[] randomParams = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter currentParameter = parameters[i];
            randomParams[i] = getRandomValue(currentParameter);
        }
        return randomParams;
    }

    private Object getRandomValue(Parameter parameter) {
        Min minAnnotation = parameter.getAnnotation(Min.class);
        Max maxAnnotation = parameter.getAnnotation(Max.class);
        NotNull notNullAnnotation = parameter.getAnnotation(NotNull.class);
        Class<?> type = parameter.getType();
        if (type == String.class) {
            return getRandomString(notNullAnnotation);
        } else if (type == Integer.class || type == int.class) {
            return getRandomInt(minAnnotation, maxAnnotation);
        } else {
            return null;
        }
    }

    private static Object getRandomString(NotNull notNullAnnotation) {
        if (notNullAnnotation != null) {
            byte[] array = new byte[STRING_LENGTH];
            RANDOM.nextBytes(array);
            return new String(array);
        }
        return null;
    }

    private static int getRandomInt(Min minAnnotation, Max maxAnnotation) {
        int min = minAnnotation != null ? minAnnotation.value() : Integer.MIN_VALUE;
        int max = maxAnnotation != null ? maxAnnotation.value() : Integer.MAX_VALUE;
        return RANDOM.nextInt(min, max);
    }
}
