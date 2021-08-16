package ru.geekbrains.jca.Lesson_14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для метода arrayAfterLastFour
 * */
class arrayAfterLastFourTest {

    /**
     * Метод, содержащий исходные тестовые данные
     **/
    private static Stream<Arguments> testArraysContainer() {
        List<Arguments> argList = new ArrayList<>();
        argList.add(Arguments.arguments(new int[] {1, 5, 8, 4, 1, 2, 2, 0}, new int[] {1, 2, 2, 0}));
        argList.add(Arguments.arguments(new int[] {4, 0, 4, 1, 0, 4, 8, 6}, new int[] {8, 6}));
        argList.add(Arguments.arguments(new int[] {7, 0, 1, 1, 0, 6, 8, 4}, new int[] {}));
        argList.add(Arguments.arguments(new int[] {4, 4, 4, 4, 8, 9}, new int[] {8, 9}));
        return argList.stream();
    }

    /**
     * Параметризированный тестовый метод, проверяющий корректную работу метода с различными массивами
     * */
    @MethodSource("testArraysContainer")
    @ParameterizedTest
    void CorrectCutTest(int[] testArr, int[] result) {
        Assertions.assertArrayEquals(result, MainClass.arrayAfterLastFour(testArr));
    }

    /**
     * Тестовый метод, проверяющий корректное пробрасывание исключения в случае входного массива без четверок
     * */
    @Test
    void TrowExceptionTest() {
        int[] testArr = {1, 5, 8, 3, 1, 2, 2, 0};
        Assertions.assertThrows(RuntimeException.class, () -> MainClass.arrayAfterLastFour(testArr));
    }
}