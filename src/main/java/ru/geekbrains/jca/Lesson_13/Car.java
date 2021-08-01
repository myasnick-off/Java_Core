package ru.geekbrains.jca.Lesson_13;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс участника гонки
 * */
public class Car implements Runnable {

    private static CyclicBarrier carBarrier;            // общий CyclicBarrier для всех участников
    private static Lock lock = new ReentrantLock();     // Lock для определения победителя
    private static boolean isStarted;                   // флаг старта гонки
    private static int CARS_COUNT;                      // счетчик созданных участников гонки

    private Race race;                                  // объект гоночной трассы
    private int speed;                                  // скорость участника
    private String name;                                // название участника


    /**
     * Метод инициализации CyclicBarrier для всех участников */
    public static void carBarrierInit() {
        isStarted = false;                                            // сбрасываем флаг старта
        carBarrier = new CyclicBarrier(CARS_COUNT, () -> {            // инициализируем carBarrier с числом участников и с barrierAction
            if (!isStarted) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                isStarted = true;
            } else
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        });
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    /**
     * Переопределенный метод run, описывающий прохождение участником гоночной трассы
     * */
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800)); // время на подготовку участника
            System.out.println(this.name + " готов");
            carBarrier.await();                             // после готовности участника пополняем счетчик барьера
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) { // в цикле запускаем прохождение всех этапов трассы
            race.getStages().get(i).go(this);
        }
        try {
            if (lock.tryLock()) {                                   // если замок открыт, закрываем его
                if (carBarrier.getNumberWaiting() == 0)             // если это участник пришедший первым:
                    System.out.println(this.name + " ВЫИГРАЛ!!!");  // выводим соответсвующее сообщение
                lock.unlock();                                      // открываем замок
            }
            carBarrier.await();                             // после финиширования участника пополняем счетчик барьера
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
