package ru.geekbrains.jca.Lesson_11;

import java.util.Arrays;
import java.util.List;

/**
 * 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
 * 2. Написать метод, который преобразует массив в ArrayList;
 * 3. Задача: Даны классы Fruit, Apple extends Fruit, Orange extends Fruit; Класс Box, в который можно складывать
 *    фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки,
 *    и апельсины; Для хранения фруктов внутри коробки можно использовать ArrayList; Сделать метод getWeight(),
 *    который высчитывает вес коробки, зная вес одного фрукта и их количество: вес яблока – 1.0f,
 *    апельсина – 1.5f (единицы измерения не важны); Внутри класса Box сделать метод compare(),
 *    который позволяет сравнить текущую коробку с той, которую подадут в compare() в качестве параметра.
 *    true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
 *    Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
 *    Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами. Соответственно,
 *    в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
 *    Не забываем про метод добавления фрукта в коробку.
 **/

public class HomeWork {
    public static void main(String[] args) {

        // тестовый массив
        String[] animals = {"cat", "dog", "snake", "tiger"};

        // блок проверки метода по первому заданию-----------------
        swapArrayElements(animals, 0, 3);
        System.out.println(Arrays.toString(animals));
        //---------------------------------------------------------

        // блок проверки метода по второму заданию-----------------
        System.out.println();
        System.out.println(arrayToList(animals).getClass().getSimpleName());
        //---------------------------------------------------------

        // блок проверки третьего задания--------------------------
        Box<Apple> boxA1 = new Box<>();                         // создаем 1-ю коробку с яблоками
        Box<Apple> boxA2 = new Box<>();                         // создаем 2-ю коробку с яблоками
        Box<Orange> boxO3 = new Box<>();                        // создаем 3-ю коробку с апельсинами

        boxA1.addFruits(new Apple(), new Apple(), new Apple()); // кладем в 1-ю коробку 3 яблока
        boxA2.addFruits(new Apple());                           // кладем во 2-ю коробку 1 яблоко
        boxO3.addFruits(new Orange(), new Orange());            // кладем в 3-ю коробку 2 апельсина

        System.out.println();
        System.out.println(boxA1);                              // выводим на экран содержимое 1-й коробки
        System.out.println(boxO3);                              // выводим на экран содержимое 3-й коробки
        System.out.println(boxA1.compare(boxO3));               // сравниваем 1-ю и 3-ю коробки по весу

        boxA1.pourInto(boxA2);                                  // перекладываем содержимое 1-й коробки во 2-ю
        System.out.println(boxA1);                              // выводим на экран содержимое 1-й коробки
        System.out.println(boxA2);                              // выводим на экран содержимое 2-й коробки
        System.out.println(boxO3.compare(boxA2));               // сравниваем 2-ю и 3-ю коробки по весу
        //---------------------------------------------------------
    }

    /**
     * Метод, который меняет два элемента массива местами.
     *      param:  array - массив любого ссылочного типа, в котором происходит перемена мест эдементов
     *              index1 - идекс первого элемента, с которым меняются местами
     *              Index2 - индекс второго элмемента, с которым меняются местами
     **/
    public static <T> void swapArrayElements(T[] array, int index1, int index2) {
        T element = array[index1];
        array[index1] = array[index2];
        array[index2] = element;
    }

    /**
     * Метод, который преобразует массив в ArrayList.
     *      param:  array - преобразуемый массив
     **/
    public static <T> List<T> arrayToList(T[] array) {
        return Arrays.asList(array);
    }
}
