package ru.geekbrains.jca.Lesson_12;

import java.util.Arrays;

/**
 * Необходимо написать два метода, которые делают следующее:
 * 1) Создают одномерный длинный массив, например:
 * 2) Заполняют этот массив единицами.
 * 3) Засекают время выполнения: long a = System.currentTimeMillis().
 * 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 *
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 *
 * 5) Проверяется время окончания метода System.currentTimeMillis().
 * 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
 *
 * Отличие первого метода от второго:
 * Первый просто бежит по массиву и вычисляет значения.
 * Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
 * В конце проверьте, что массивы получились одинаковые (например Arrays.equals())
 * */

public class HomeWork {

    static final int SIZE = 10_000_000;         // длина массивов
    static final int HALF = SIZE/2;             // половина длины массивов
    static final float VAL = 1.0f;              // значение для заполнения массивов


    public static void main(String[] args) throws InterruptedException {
        System.out.println(Arrays.equals(singleThreadMethod(), doubleThreadMethod()));
    }

    /**
     * Метод создает длинный массив единиц, в ОДНОМ потоке производит математические вычисления с каждым элементом
     * и вычисляет общее время выполнения всех операций над массивом
     * return:  созданный массив после всех вычислений
     * */
    private static float[] singleThreadMethod() {
        float[] arr = getFloatArray(SIZE, VAL);
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
        }
        long finish = System.currentTimeMillis();
        System.out.printf("Total time of single-thread method: %d ms\n", (finish-start));
        return arr;
    }

    /** (Вариант 1, без разбиения и склейки массива)
     * Метод создает длинный массив единиц, в ДВУХ потоках производит математические вычисления с каждым элементом
     * и вычисляет общее время выполнения всех операций над массивом
     * return:  созданный массив после всех вычислений
     * */
    private static float[] doubleThreadMethod() throws InterruptedException {
        float[] arr = getFloatArray(SIZE, VAL);     // генерируем длинный массив единиц
        long start = System.currentTimeMillis();    // фиксируем время начала всех операций над массивом

        // Создаем первый поток, который будет обрабатывать первую половину массива
        Thread firstThread = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
            }
        });
        // Создаем второй поток, который будет обрабатывать вторую половину массива
        Thread secondThread = new Thread(() -> {
            for (int i = HALF; i < SIZE; i++) {
                arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
            }
        });
        /* Не уверен, что можно к одному и тому же массиву одновременно обращаться из разных потоков
           (хоть и к разным его элементам), но результирующий массив вроде идентичен массиву из однопоточного метода.
           И все же на всякий случай сделал еще один вариант (см. ниже) с разбиением и склейкой массива.
        */
        firstThread.start();                        // запускаем первый поток
        secondThread.start();                       // запускаем второй поток
        firstThread.join();                         // ждем пока выполнится первый поток
        secondThread.join();                        // ждем пока выполнится второй поток
        long finish = System.currentTimeMillis();   // фиксируем время окончания выполнения всех процессов над массивом
        System.out.printf("Total time of double-thread method: %d ms\n", (finish-start));
        return arr;
    }

    /** (Вариант 2, с разбиением массива на два и последующей его склейкой)
     * Метод создает длинный массив единиц, в ДВУХ потоках производит математические вычисления с каждым элементом
     * и вычисляет общее время выполнения всех операций над массивом
     * return:  созданный массив после всех вычислений
     * */
    private static float[] doubleThreadMethod1() throws InterruptedException {
        float[] arr = getFloatArray(SIZE, VAL);             // генерируем длинный массив единиц
        long start = System.currentTimeMillis();            // фиксируем время начала всех операций над массивом
        float[] halfArr = Arrays.copyOf(arr, arr.length);   // создаем новый массив и копируем туда вторую половину от основного

        // Создаем первый поток, который будет обрабатывать первую половину массива
        Thread firstThread = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
            }
        });
        // Создаем второй поток, который будет обрабатывать вторую половину массива
        Thread secondThread = new Thread(() -> {
            for (int i = HALF; i < SIZE; i++) {
                halfArr[i] = (float)(halfArr[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
            }
        });
        firstThread.start();                        // запускаем первый поток
        secondThread.start();                       // запускаем второй поток
        firstThread.join();                         // ждем пока выполнится первый поток
        secondThread.join();                        // ждем пока выполнится второй поток
        for (int i = HALF; i < SIZE; i++)           // в цикле склеиваем массивы в один
            arr[i] = halfArr[i];
        long finish = System.currentTimeMillis();   // фиксируем время окончания выполнения всех процессов над массивом
        System.out.printf("Total time of double-thread method: %d ms\n", (finish-start));
        return arr;
    }

    /**
     * Метод генерирует массив типа float
     * param:   size - размер массива
     *          value - значение, которым заполняется массив
     * return:  массив переданной длины, заполненный переданным значением
     * */
    private static float[] getFloatArray(int size, float value) {
        float[] array = new float[size];
        Arrays.fill(array, value);
        return array;
    }
}
