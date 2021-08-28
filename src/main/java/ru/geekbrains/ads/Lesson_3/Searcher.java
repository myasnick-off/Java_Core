package ru.geekbrains.ads.Lesson_3;

/**
 * 1. Дан массив из n элементов, начиная с 1. Каждый следующий элемент равен (предыдущий + 1).
 * Но в массиве гарантированно 1 число пропущено. Необходимо вывести на экран пропущенное число.
 */

public class Searcher {
    public int missingValueSearch(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        int base;
        int step = 0;

        if (arr.length == 0) {
            System.out.printf("Array is empty! Finished for %d steps\n", step);
            return 1;

        }

        while (end >= start) {
            step++;
            base = (start + end) / 2;

            if (arr[base] == base + 1) {
                start = base + 1;
            } else if (arr[base] > base + 1) {
                if (arr[base] - 1 == arr[base - 1]) {
                    end = base - 1;
                } else {
                    System.out.printf("Finished for %d steps\n", step);
                    return arr[base] - 1;
                }
            }
        }
        System.out.printf("Not found! Finished for %d steps\n", step);
        return -1;
    }
}
