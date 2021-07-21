package ru.geekbrains.jca.Lesson_10;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
 *    Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 *    Посчитать, сколько раз встречается каждое слово.
 * 2. Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных
 *    номеров. В этот телефонный справочник с помощью метода add() можно добавлять записи,
 *    а с помощью метода get() искать номер телефона по фамилии. Следует учесть,
 *    что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
 *    тогда при запросе такой фамилии должны выводиться все телефоны.
 *    Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес),
 *    взаимодействие с пользователем через консоль и т.д).
 *    Консоль использовать только для вывода результатов проверки телефонного справочника.
 */

public class Main {
    public static void main(String[] args) {

        // тестовый массив для первой задачи
        String[] words = {"apple", "orange", "lemon", "banana", "apple", "orange", "nut",
                "kiwi", "cherry", "nut", "kiwi", "apple", "leak", "kiwi", "mango", "mango",
                "nut", "lemon", "pea", "mango", "apple", "pea", "apple", "kiwi", "mango" };

        arrayHandler(words);


        // блок проверки второй задачи
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Ivanov", 9001234512L);
        phoneBook.add("Petrov", 9003218777L);
        phoneBook.add("Ivanov", 9009871523L);
        phoneBook.add("Pupkon", 9005648475L);
        phoneBook.add("Ivanov", 9006661524L);
        phoneBook.add("Popov", 9003354899L);
        phoneBook.add("Zotov", 9002541010L);

        phoneBook.get("Ivanov");
    }

    /**
     * Метод выводит список уникальных слов в массиве и число их повторний
     * param - массив слов
     * */
    public static void arrayHandler(String[] words) {
        Map<String, Integer> wordsMap = new HashMap<>();                // создаем мапу, где ключ - уникальное слово из массива, значение - число повторений этого слова
        for (String word : words) {                                     // проходим по всем элементам переданного массива
            if (wordsMap.putIfAbsent(word, 1) != null)                  // если добавляемое слово уже добавлено ранее
                wordsMap.put(word, wordsMap.get(word) + 1);             // перезаписываем его в мапе с прежним количеством, увеличенным на единицу
        }
        wordsMap.forEach((K, V) -> System.out.println(K + " : " + V));  // выводим все уникальные слова и их количество в массиве на экран
    }
}
