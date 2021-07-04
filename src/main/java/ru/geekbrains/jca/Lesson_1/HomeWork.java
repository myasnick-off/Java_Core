package ru.geekbrains.jca.Lesson_1;

public class HomeWork {
    public static void main(String[] args) {

        // применение методов, реализованных ниже:
        System.out.println("Результат: " + mathSolution(12f, 0.2f, 2.16f, 84.04f));
        System.out.println(checkRange(15, 5));
        checkSign(62);
        System.out.println(isNegative(-9));
        greetings("Дима");
        leapYearCheck(2004);
    }

    /* 1. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
          где a, b, c, d – аргументы этого метода, имеющие тип float. */
    private static float mathSolution(float a, float b, float c, float d) {
        float result = a * (b + (c / d));
        return result;
    }

    /* 2. Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах
    от 10 до 20 (включительно), если да – вернуть true, в противном случае – false.*/
    private static boolean checkRange(int a, int b) {
        int sum = a + b;
        if (sum >= 10 && sum <= 20) return true;
        else return false;
    }

    /* 3. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать
    в консоль, положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.*/
    private static void checkSign(int val) {
        if (val < 0) System.out.println("Число " + val + " отрицательное");
        else System.out.println("Число " + val + " положительное");
    }

    /* 4. Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true,
    если число отрицательное, и вернуть false если положительное.*/
    private static boolean isNegative(int val) {
        if (val < 0) return true;
        else return false;
    }

    /* 5. Написать метод, которому в качестве параметра передается строка, обозначающая имя.
    Метод должен вывести в консоль сообщение «Привет, указанное_имя!».*/
    private static void greetings(String name) {
        System.out.println("Привет, " + name + "!");
    }

    /* *6. Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль.
    Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный. */
    private static void leapYearCheck(int year) {
        boolean isLeap = true;
        if ((year % 4 != 0) || ((year % 100 == 0) && (year % 400 != 0)))
            System.out.println("Год не високосный!");
        else System.out.println("Год високосный!");
    }
}