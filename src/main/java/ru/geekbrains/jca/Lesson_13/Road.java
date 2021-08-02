package ru.geekbrains.jca.Lesson_13;

/**
 * Класс дороги, как одной из разновидностей этапов гоночной трассы
 * */
public class Road extends Stage {

    /**
     * Конструктор
     * */
    public Road(int length) {
        this.length = length;                                   // инициализируем длину дороги
        this.description = "Дорога " + length + " метров";      // инициализируем описание дороги
    }

    /**
     * Метод прохождения дороги участниками гонки
     * param:   c - участник гонки
     * */
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000L);    // проезжаем дорогу в соответсвие со скоростью данного участника
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
