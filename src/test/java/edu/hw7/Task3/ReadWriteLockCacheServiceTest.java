package edu.hw7.Task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReadWriteLockCacheServiceTest {

    private ReadWriteLockCacheService cacheService;

    @BeforeEach
    void setUp() {
        cacheService = new ReadWriteLockCacheService();
    }

    @Test
    void add_test() {
        Person person = new Person(8, "name", "address", "number");

        cacheService.add(person);
        Person result = cacheService.findByName("name");

        assertThat(result).isEqualTo(person);
    }

    @Test
    void delete_test() {
        Person person = new Person(8, "name", "address", "number");
        cacheService.add(person);

        cacheService.delete(8);
        Person result = cacheService.findByName("name");

        assertThat(result).isNull();
    }

    @Test
    void findByName_test() {
        Person person = new Person(8, "name", "address", "number");
        cacheService.add(person);

        Person result = cacheService.findByName("name");

        assertThat(result).isEqualTo(person);
    }

    @Test
    void findByAddress_test() {
        Person person = new Person(8, "name", "address", "number");
        cacheService.add(person);

        Person result = cacheService.findByAddress("address");

        assertThat(result).isEqualTo(person);
    }

    @Test
    void findByPhone_test() {
        Person person = new Person(8, "name", "address", "number");
        cacheService.add(person);

        Person result = cacheService.findByPhone("number");

        assertThat(result).isEqualTo(person);
    }
}
