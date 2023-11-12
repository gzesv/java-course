package edu.hw3.Task8;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator<T> {
    private final Iterator<T> backwardIterator;

    BackwardIterator(List<T> list) {
        this.backwardIterator = list.reversed().iterator();
    }

    @Override
    public boolean hasNext() {
        return backwardIterator.hasNext();
    }

    @Override
    public T next() {
        return backwardIterator.next();
    }
}
