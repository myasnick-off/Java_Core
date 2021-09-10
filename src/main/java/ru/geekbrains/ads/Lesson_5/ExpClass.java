package ru.geekbrains.ads.Lesson_5;

import java.math.BigInteger;

/**
 * 1. Написать программу по возведению числа в степень с помощью рекурсии.
 **/

public class ExpClass {

    // Рекурсивный метод по позведению числа в степень
    //      а - число, возводимое в степень
    //      n - показатель степени
    public BigInteger exp(BigInteger a, int n) {
        if (n == 1) {
            return a;
        } else {
            return a.multiply(exp(a, --n));
        }
    }
}
