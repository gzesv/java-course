package edu.hw10.Task2;

import edu.hw10.Task2.annotations.Cache;

public interface CacheTestFile {
    @Cache(persist = true)
    Integer test(Integer num);
}
