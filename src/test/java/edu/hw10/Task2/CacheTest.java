package edu.hw10.Task2;

import edu.hw10.Task2.annotations.Cache;

public interface CacheTest {
    @Cache(persist = false)
    Integer test(Integer num);
}
