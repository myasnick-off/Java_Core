package ru.geekbrains.jca.Lesson_13;

/**
 * Абстрактный класс этапа гоночной трассы
 * */
public abstract class Stage {
    protected int length;               // длина этапа
    protected String description;       // описание этапа

    public String getDescription() {
        return description;
    }

    /**
     * Метод прохождения этапа участниками гонки
     * param:   c - участник гонки
     * */
    public abstract void go(Car c);
}
