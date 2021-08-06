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
 * Тестовый класс для метода containsOneAndFour
 * */
class containsOneAndFourTest {


    /**
     * Тестовый метод для отработки результата true
     * */
    @Test
    void containsOneAndFourTest() {
        int[] testArr = {1, 1, 1, 1, 4, 1, 4};
        Assertions.assertTrue(MainClass.containsOneAndFour(testArr));
    }

    /**
     * Параметризированный тестовый метод для отработки результата false с различными входными массивами
     * */
    @MethodSource("testArraysContainer")
    @ParameterizedTest
    void allFalseSituationsTest(int[] testArr) {
        Assertions.assertFalse(MainClass.containsOneAndFour(testArr));
    }

    /**
     * Метод, содержащий исходные тестовые данные
     **/
    private static Stream<Arguments> testArraysContainer() {
        List<Arguments> argList = new ArrayList<>();
        argList.add(Arguments.arguments(new int[] {4, 4, 4, 4, 4, 4, 4, 4}));
        argList.add(Arguments.arguments(new int[] {1, 1, 1, 1, 1, 1, 1, 1}));
        argList.add(Arguments.arguments(new int[] {1, 1, 8, 4, 1, 1, 1, 4}));
        return argList.stream();
    }
}
