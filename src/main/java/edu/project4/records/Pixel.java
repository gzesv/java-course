package edu.project4.records;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pixel {
    public Lock lock = new ReentrantLock();
    private Color color;
    private int hitCount;

    public Pixel(Color color, int hitCount) {
        this.color = color;
        this.hitCount = hitCount;
    }

    public Pixel() {
        this(new Color(0, 0, 0), 0);
    }

    public Color color() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void mixColor(Color other) {
        color = color.mix(other);
    }

    public void hit() {
        hitCount++;
    }

    public int hitCount() {
        return hitCount;
    }
}
