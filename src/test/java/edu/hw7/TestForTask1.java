package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForTask1 {
    @ParameterizedTest
    @CsvSource({"3,2", "11,2", "15,14", "10,10", "126,131", "12345,123", "32,16", "50,1"})
    void test(int number, int threads) throws InterruptedException {
        int actualResult = Task1.incrementParallel(number, threads);

        assertEquals(number + threads, actualResult);
    }
}
