package edu.hw10.Task1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RandomObjectGeneratorTest {
    @Test
    void generatePOJO_test() {
        RandomObjectGenerator generator = new RandomObjectGenerator();

        assertDoesNotThrow(() -> {
            TestClass object = (TestClass) generator.nextObject(TestClass.class);

            assertThat(object).isNotNull();
            assertThat(object.getWord()).isNotNull();
            assertThat(object.getNumber()).isBetween(1, 1111);
        });
    }

    @Test
    void generateRecord_test() {
        RandomObjectGenerator generator = new RandomObjectGenerator();

        assertDoesNotThrow(() -> {
            TestRecord record = (TestRecord) generator.nextObject(TestRecord.class);

            assertThat(record).isNotNull();
            assertThat(record.word()).isNotNull();
            assertThat(record.number()).isBetween(17, 357);
        });
    }

    @Test
    void generateByFactory_test() {
        RandomObjectGenerator generator = new RandomObjectGenerator();

        assertDoesNotThrow(() -> {
            TestClass object = (TestClass) generator.nextObject(TestClass.class, "create");

            assertThat(object).isNotNull();
            assertThat(object.getWord()).isNotNull();
            assertThat(object.getNumber()).isBetween(7, 250);
        });
    }

}
