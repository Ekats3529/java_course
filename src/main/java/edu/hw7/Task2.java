package edu.hw7;

import java.util.stream.IntStream;

public class Task2 {
    private Task2() {
    }

    public static int calculateFactorial(int num) {
        return IntStream.rangeClosed(1, num).parallel().reduce((x, y) -> x * y).orElse(1);
    }

}
