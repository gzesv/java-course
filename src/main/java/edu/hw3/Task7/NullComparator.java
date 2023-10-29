package edu.hw3.Task7;

import java.util.Comparator;

public class NullComparator<T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T a, T b) {
        if (a == null && b == null) {
            return 0;
        }
        if (a == null) {
            return -1;
        }
        if (b == null) {
            return 1;
        }
        return a.compareTo(b);
    }
}
