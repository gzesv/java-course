package edu.hw3.Task6;

import java.util.Objects;

public class Stock implements Comparable<Stock> {
    private final String name;

    private final double price;

    public Stock(String name, double rublePrice) {
        this.name = name;
        this.price = rublePrice;
    }

    @Override
    public int compareTo(Stock other) {
        return Double.compare(price, other.price);
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        return hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
