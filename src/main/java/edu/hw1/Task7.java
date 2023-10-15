package edu.hw1;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task7 {

    public static int rotateLeft(int n, int inputShift) {
        if (n < 0 || inputShift < 0) {
            return -1;
        }

        int bitCount = Integer.toBinaryString(n).length();
        int shift = inputShift % bitCount;

        if (shift == 0) {
            return n;
        }

        int divisor = (int) Math.pow(2, bitCount - shift);

        int leftPart = (n % divisor) << shift;
        int rightPart = n >>> (bitCount - shift);

        return leftPart | rightPart;
    }

    public static int rotateRight(int n, int inputShift) {
        if (n < 0 || inputShift < 0) {
            return -1;
        }

        int bitCount = Integer.toBinaryString(n).length();
        int shift = inputShift % bitCount;

        if (shift == 0) {
            return n;
        }

        int divisor = (int) Math.pow(2, shift);

        int leftPart = (n % divisor) << (bitCount - shift);
        int rightPart = n >>> shift;

        return leftPart | rightPart;

    }
}
