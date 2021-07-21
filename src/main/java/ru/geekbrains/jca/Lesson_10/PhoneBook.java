package ru.geekbrains.jca.Lesson_10;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс телефонного справочника
 * fields:   phoneMap - мапа, где ключ - телефонный номер, значение - фамилия владельца
 * */
public class PhoneBook {

    private Map<Long, String> phoneMap = new HashMap<>();

    /**
     * Метод добавляет новую запись в телефонный справочник
     * param:    name - фамилия
     *           number - телефонный номер
     * */
    public void add(String name, Long number) {
        phoneMap.put(number, name);
    }

    /**
     * Метод ведет поиск телефонного номера по фимилии. Если есть однофамильцы, выдает все номера по этой фамилии
     * param:    name - искомая фамилия
     * */
    public void get(String name) {
        boolean isFound = false;                                    // флаг успешного результата поиска
        for (Map.Entry<Long, String> entry : phoneMap.entrySet()) { // проходим по всем entry мапы справочника
            if (name.equals(entry.getValue())) {                    // если значение entry соответствует искомой фамилии
                System.out.println(name + " : " + entry.getKey());  // выводим фамилию и ее телефон на экран
                isFound = true;                                     // устанавливаем флаг успешного поиска
            }
        }
        if (!isFound) System.out.printf("Person with name %s is not found!\n", name); // если ничего не найдено, выводим соответствующее сообщение
    }
}
