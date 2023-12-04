package edu.hw7.Task4;

import java.security.SecureRandom;
import java.util.Random;

@SuppressWarnings({"HideUtilityClassConstructor", "MagicNumber"})
public class MonteCarloMethodSingleThread {
    private static final Random RANDOM = new SecureRandom();

    public static double calculatePi(int totalCount) {
        int circleCount = 0;
        for (int i = 0; i < totalCount; i++) {
            double x = RANDOM.nextDouble(0, 1);
            double y = RANDOM.nextDouble(0, 1);
            if (isInCircle(new Point(x, y))) {
                circleCount++;
            }
        }
        return 4 * ((double) circleCount / totalCount);
    }

    private static boolean isInCircle(Point point) {
        return Math.pow(point.x(), 2) + Math.pow(point.y(), 2) < 1;
    }
}
