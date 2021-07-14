package ru.geekbrains.jca.Lesson_7;

/** класс кота */
public class Cat {

    private String name;                // имя кота
    private int appetite;               // уровень аппетита кота
    private int hungryValue;            // уровень голода кота

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    /** метод, описывающий поедание котом еды из миски
     * bowl - миска, из которой ест кот */
    public void eat(Bowl bowl) {
        hungryValue = bowl.decrease(appetite);                                    // вызвав метод усеньшения еды в миске, определяем уровень голода кота
        System.out.printf("Cat %s has ate %d peaces of food.\n", name, appetite - hungryValue);
        if (hungryValue > 0)                                                      // если кот все еще голоден:
            System.out.printf("Not enough food!!! Cat %s still hungry!\n", name); // выводим данное сообщение
        else                                                                      // иначе:
            System.out.printf("Cat %s is full!\n", name);                         // выводим данное сообщение
    }
}
