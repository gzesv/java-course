package edu.hw2.Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RectangleAndSquareTest {
    private static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Площадь прямоугольника")
    public void test1(Rectangle rectangle) {
        Rectangle result = rectangle
            .setWidth(20.0)
            .setHeight(10.0);

        assertThat(result.getArea()).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Площадь квадрата")
    void test2(){
        Square square = new Square(0);

        square = (Square) square.setSide(10);
        double area = square.getArea();

        assertThat(area).isEqualTo(100);
    }
}
