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

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Stock stock = (Stock) o;

        if (Double.compare(price, stock.price) != 0) {
            return false;
        }
        return Objects.equals(name, stock.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
