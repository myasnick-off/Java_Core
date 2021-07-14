package ru.geekbrains.jca.Lesson_8;

import ru.geekbrains.jca.Lesson_8.Obstacle.Track;
import ru.geekbrains.jca.Lesson_8.Obstacle.Wall;

/** Интерфейс, описывающий движения */
public interface Movable {

    /** метод бега
     * принимает в качестве параметра объект типа Track */
    boolean run(Track track);

    /** метод прыжка
     * принимает в качестве параметра объект типа Wall */
    boolean jump(Wall wall);
}
