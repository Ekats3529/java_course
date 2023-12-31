package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForTask7 {
    Task7 task7 = new Task7();
    @Test
    public final void test1() {

        int expectedResult = 4;

        int actualResult = task7.rotateRight(8, 1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public final void test2() {

        int expectedResult = 32;

        int actualResult = task7.rotateLeft(16, 1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public final void test3() {

        int expectedResult = 68;

        int actualResult = task7.rotateLeft(17, 2);

        assertEquals(expectedResult, actualResult);
    }
}
