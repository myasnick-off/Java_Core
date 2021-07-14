package ru.geekbrains.jca.Lesson_7;

/** Расширенный вариант решения задачи с численным значением степени голода котов (вместо Булева)
 * и с конечным объемом миски */
public class Main {

    public static void main(String[] args) {

        Bowl bowl = new Bowl(148);                  // создаем новую миску с объемом 148
        Cat[] cats = {  new Cat("Pushok", 30),   // создаем массив котов с разным аппетитом
                        new Cat("Murka", 20),
                        new Cat("Bagira", 40),
                        new Cat("Tom", 45) };

        bowl.fill(200);                          // наполняем миску
        while (!bowl.isEmpty()) {                          // пока миска не пуста
            for (Cat cat : cats) {                         // проходим по всем котам
                cat.eat(bowl);                             // даем котам поесть из миски
                bowl.checkFood();                          // проверяем наличие еды в миске
                if (bowl.isEmpty()) break;                 // если миска пуста, прерываем цикл
            }
        }
    }
}
