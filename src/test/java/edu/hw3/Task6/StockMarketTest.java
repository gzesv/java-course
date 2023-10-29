package edu.hw3.Task6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockMarketTest {
    @Test
    @DisplayName("Вывод самой дорогой акции")
    void stockMarketSort() {
        Market market = new Market();
        market.add(new Stock("A", 3333.0));
        market.add(new Stock("B", 1111.0));
        market.add(new Stock("C", 2222.0));
        Stock exepted = new Stock("A", 3333.0);

        Stock mostValuableStock = market.mostValuableStock();

        Assertions.assertThat(mostValuableStock).isEqualTo(exepted);
    }

    @Test
    @DisplayName("Тест удаления")
    void remove_test() {
        Market market = new Market();
        market.add(new Stock("A", 3333.0));
        market.add(new Stock("B", 1111.0));
        market.add(new Stock("C", 2222.0));

        Stock oldmostValuableStock = market.mostValuableStock();

        market.remove(new Stock("A", 3333.0));

        Stock mostValuableStock = market.mostValuableStock();

        Assertions.assertThat(oldmostValuableStock).isEqualTo(new Stock("A", 3333.0));
        Assertions.assertThat(mostValuableStock).isEqualTo(new Stock("C", 2222.0));
    }

    @Test
    @DisplayName("Тест stock equals")
    void stockEquals_test() {
        Stock stock = new Stock("A", 3333.0);
        Object noStock = new Object();

        boolean isEquals1 = stock.equals(noStock);
        boolean isEquals2 = stock.equals(stock);

        Assertions.assertThat(isEquals1).isFalse();
        Assertions.assertThat(isEquals2).isTrue();
    }
}
