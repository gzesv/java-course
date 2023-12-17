package edu.hw11.Task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class ArithmeticUtilsTest {
    @Test
    void delegateRedefineSum() throws Exception {
        ByteBuddyAgent.install();
        Class<?> Redefined = new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(named("sum"))
            .intercept(MethodDelegation.to(RedefinedArithmeticUtils.class))
            .make()
            .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
            .getLoaded();

        ArithmeticUtils redefined = (ArithmeticUtils) Redefined.getDeclaredConstructor().newInstance();
        assertThat(redefined.sum(7, 7)).isEqualTo(49);
    }
}
