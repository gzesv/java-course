package edu.project4.records;

import java.util.Random;

public record Color(int r, int g, int b) {
    private final static int BOUND = 256;

    public Color mix(Color other) {
        int newR = (r + other.r) / 2;
        int newG = (g + other.g) / 2;
        int newB = (b + other.b) / 2;
        return new Color(newR, newG, newB);
    }

    public static Color generate() {
        Random random = new Random();

        int r = random.nextInt(0, BOUND);
        int g = random.nextInt(0, BOUND);
        int b = random.nextInt(0, BOUND);

        return new Color(r, g, b);
    }
}
