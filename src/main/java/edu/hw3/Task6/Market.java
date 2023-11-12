package edu.hw3.Task6;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Market implements StockMarket {
    private final Queue<Stock> stocks;

    public Market() {
        this.stocks = new PriorityQueue<>(Collections.reverseOrder());
    }

    @Override
    public void add(Stock stock) {
        this.stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        this.stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return this.stocks.peek();
    }

}
