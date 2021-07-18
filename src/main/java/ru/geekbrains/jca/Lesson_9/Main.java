package ru.geekbrains.jca.Lesson_9;

/**
 * 1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
 *    При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
 *    Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ
 *    или текст вместо числа), должно быть брошено исключение MyArrayDataException с детализацией,
 *    в какой именно ячейке лежат неверные данные.
 * 3. В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException и
 *    MyArrayDataException и вывести результат расчета.
 */
public class Main {

    public static final int ARRAY_SIZE = 4;         // требуемый размер массива

    public static void main(String[] args) {

        // тестовый двумерный массив для проверки 1-го варианта метода
        String[][] matrix1 = {
                {"15", "0", "-108", "74"},
                {"19", "52", "11", "-9"},
                {"-20", "1", "16", "92"},
                {"35", "-62", "56", "+28"}
        };
        // тестовый двумерный массив для проверки 2-го варианта метода
        String[][] matrix2 = {
                {"25", "1", "-98", "70"},
                {"29", "42", "18", "-9"},
                {"-20", "0", "16", "82"},
                {"39", "-82", "66", "+21"}
        };
        try {
            sumOfArrayElements1(matrix1);     // применение 1-го варианта метода
            sumOfArrayElements2(matrix2);     // применение 2-го варианта метода
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод вычисления арифметической суммы всех элементов двумерного массива, являющихся строками.
     * Вариант 1 (с обработкой NumberFormatException) */
    public static void sumOfArrayElements1(String[][] matrix) throws MyArraySizeException, MyArrayDataException {
        if (matrix.length != ARRAY_SIZE || matrix[0].length != ARRAY_SIZE) //если размеры матрицы превышают требуемый:
            throw new MyArraySizeException(ARRAY_SIZE);                    // кидаем наше исключение по размеру матрицы
        int sum = 0;                                                       // объявляем переменную итоговой суммы
        for (int i = 0; i < matrix.length; i++) {                          // в двойном цикле проходим по всем элементам матрицы
            for (int j = 0; j < matrix[0].length; j++) {
                int num;
                try {
                    num = Integer.parseInt(matrix[i][j]);             // пробуем парсить по Integer значение каждого элемента матрицы
                } catch (NumberFormatException e) {                   // если элемент не парсится, то ловим исключение NumberFormatException
                    throw new MyArrayDataException(i, j);             // кидаем наше исключение по формату данных в матрице
                }
                sum += num;                                           // суммируем полученное числовое значение с итоговым
            }
        }
        System.out.printf("Var 1. Sum of array's elements is: %d\n", sum);
    }

    /**
     * Метод вычисления арифметической суммы всех элементов двумерного массива, являющихся строками.
     * Вариант 2 (с применением регулярных выражений) */
    public static void sumOfArrayElements2(String[][] matrix) throws MyArraySizeException, MyArrayDataException {
        if (matrix.length != ARRAY_SIZE || matrix[0].length != ARRAY_SIZE) //если размеры матрицы превышают требуемый:
            throw new MyArraySizeException(ARRAY_SIZE);                    // кидаем наше исключение по размеру матрицы
        int sum = 0;                                                       // объявляем переменную итоговой суммы
        for (int i = 0; i < matrix.length; i++) {                          // в двойном цикле проходим по всем элементам матрицы
            for (int j = 0; j < matrix[0].length; j++) {
                if (!matrix[i][j].matches("^([+,-]?)\\d+"))          // если значение элемента матрицы не соответствует регулярному выражению, описывающему целые числа
                    throw new MyArrayDataException(i, j);                  // кидаем наше исключение по формату данных в матрице
                sum += Integer.parseInt(matrix[i][j]);                     // суммируем полученное числовое значение с итоговым
            }
        }
        System.out.printf("Var 2. Sum of array's elements is: %d\n", sum);
    }
}
