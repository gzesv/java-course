package edu.hw11.Task1;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldTest {
    @Test
    void helloWorld_test() throws Exception {
        Class<?> HelloByteBuddy = new ByteBuddy()
            .subclass(Object.class)
            .name("HelloByteBuddy")
            .method(named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        Object helloByteBuddy = HelloByteBuddy.getDeclaredConstructor().newInstance();

        assertThat(helloByteBuddy.toString()).isEqualTo("Hello, ByteBuddy!");
    }
}
