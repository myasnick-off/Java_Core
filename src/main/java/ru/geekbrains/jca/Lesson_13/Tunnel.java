package ru.geekbrains.jca.Lesson_13;

import java.util.concurrent.Semaphore;

/**
 * Класс тоннеля, как одной из разновидностей этапов гоночной трассы
 * */
public class Tunnel extends Stage {

    private Semaphore semaphore;        // объект семафора для данного тоннеля

    /**
     * Конструктор
     * */
    public Tunnel() {
        this.length = 80;                                           // инициализируем длину тоннеля
        this.description = "Тоннель " + length + " метров";         // инициализируем описание тоннеля
        semaphore = new Semaphore(MainClass.CARS_COUNT/2);   // инициализируем семафор с количеством разрешений, равному половине всех участников
    }

    /**
     * Метод прохождения тоннеля участниками гонки
     * param:   c - участник гонки
     * */
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire();    // получаем разрешение у семафора
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000L);    // проезжаем тоннель в соответсвие со скоростью данного участника
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release();        // освобождаем семафор
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
