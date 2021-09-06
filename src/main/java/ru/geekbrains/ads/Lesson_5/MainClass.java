package ru.geekbrains.ads.Lesson_5;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        // Проверка работы рекурсивного метода возведения числа в степень
        expTest(2, 10);
        expTest(8, 32);
        expTest(3, 16);
        //--------------------------------------------------

        // Проверка решения задачи о рюкзаке
        System.out.println();
        backpackTest();
    }

    // Тестовый метод для проверки 1-го задания (рекурсивный метод возведения числа в степень)
    private static void expTest(int num, int n) {
        ExpClass expExample = new ExpClass();
        BigInteger a = new BigInteger(String.valueOf(num));
        BigInteger result = expExample.exp(a, n);
        System.out.printf("%d в степени %d равно: %d\n", num, n, result);
    }

    // Тестовый метод для проверки 2-го задания («Задача о рюкзаке» с помощью рекурсии)
    private static void backpackTest() {
        Item[] items = {
                new Item("notebook", 500, 3),
                new Item("vase", 400, 5),
                new Item("statuette", 1200, 4),
                new Item("tablet", 300, 1),
                new Item("TV", 800, 8),
                new Item("watch", 700, 6)
        };
        Backpack backpack = new Backpack(10);
        System.out.println(backpack.pack(Arrays.asList(items)));
        System.out.println("Max cost: " + backpack.getMaxCost());
    }
}
