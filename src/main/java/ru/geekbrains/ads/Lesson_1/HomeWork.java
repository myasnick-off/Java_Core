package ru.geekbrains.ads.Lesson_1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Определить сложность следующих алгоритмов:
 * <p>
 * -. Поиск элемента массива с известным индексом
 * Ответ: O(1)
 * <p>
 * -. Дублирование одномерного массива через foreach
 * Ответ: O(n)
 * <p>
 * -. Удаление элемента массива с известным индексом без сдвига
 * Ответ: O(1)
 * <p>
 * -. Удаление элемента массива с неизвестным индексом без сдвига
 * Ответ: O(n)
 * <p>
 * -. Удаление элемента массива с неизвестным индексом со сдвигом
 * Ответ: O(n)
 */

public class HomeWork {
    public static void main(String[] args) {

        A();
        B();
        C();
        factorial(BigInteger.valueOf(10000));
        fib(BigInteger.valueOf(50));
    }

    /**
     * Определить сложность следующих алгоритмов. Сколько произойдет итераций?
     **/

    // a)
    public static void A() {
        int n = 10000;                                  // O(1)
        List<Integer> arrayList = new ArrayList<>();    // O(1)
        for (int i = 0; i < n; i++) {                   // O(n)
            for (int j = 1; j < n; j *= 2) {            // O(log(n))
                arrayList.add(i * j);                   // O(1)
            }
        }
    }
    // Итого: сложнсть данного алгоритма O(1) + O(1) + O(n) * O(log(n + 1)) = O(n*log(n))


    // b)
    public static void B() {
        int n = 10000;                                  // O(1)
        List<Integer> arrayList = new ArrayList<>();    // O(1)
        for (int i = 0; i < n; i += 2) {                // O(n/2)
            for (int j = i; j < n; j++) {               // O(n/2)
                arrayList.add(i * j);                   // O(1)
            }
        }
    }
    // Итого: сложнсть данного алгоритма O(1) + O(1) + O(n/2) * O((n+1)/2) = O(n^2)


    // c)
    public static void C() {
        int n = 10000;                                  // O(1)
        List<Integer> arrayList = new ArrayList<>();    // O(1)
        for (int i = 0; i < n; i++) {                   // O(n)
            for (int j = 0; j < n; j++) {               // O(n/2)
                arrayList.add(i * j);                   // O(1)
                n--;                                    // O(1)
            }
        }
    }
    // Итого: сложнсть данного алгоритма O(n)


    // d)
    public static BigInteger factorial(BigInteger n) {
        if (n.equals(BigInteger.ONE)) {                                     // O(1)
            return n;
        }
        return n.multiply(factorial(n.subtract(BigInteger.valueOf(1))));    // O(n)
    }
    // Итого: сложнсть данного алгоритма O(1) + O(n) = O(n)


    // e)
    public static BigInteger fib(BigInteger n) {
        if (n.equals(BigInteger.ONE)) {                                                 // O(1)
            return BigInteger.ZERO;
        }
        if (n.equals(BigInteger.TWO)) {                                                 // O(1)
            return BigInteger.ONE;
        }
        return fib(n.subtract(BigInteger.ONE)).add(fib(n.subtract(BigInteger.TWO)));    // O(2^n)
    }
    // Итого: сложнсть данного алгоритма O(1) + O(1) + O(2^n) = O(2^n)

}
