package ru.geekbrains.jca.Lesson_8;

import ru.geekbrains.jca.Lesson_8.Obstacle.Track;
import ru.geekbrains.jca.Lesson_8.Obstacle.Wall;

public class Robot implements Movable {

    private String name;        // имя робота
    private int maxDistance;    // максимальная дистанция, которую он может пробежать
    private int maxHeight;      // максимальная высота, которую он может перепрыгнуть

    public Robot(String name, int maxDistance, int maxHeight) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }


    @Override
    public boolean jump(Wall wall) {
        if (wall.getHeight() > maxHeight) {
            System.out.printf("This wall too high for %s! Robot out of the race!\n", name);
            return false;
        }
        System.out.printf("Robot %s jumped over %d meter(s)\n", name, wall.getHeight());
        return true;
    }

    public boolean run(Track track) {
        if (track.getLength() > maxDistance) {
            System.out.printf("This track too long for %s! Robot out of the race!\n", name);
            return false;
        }
        System.out.printf("Robot %s had run %d meters\n", name, track.getLength());
        return true;
    }
}
