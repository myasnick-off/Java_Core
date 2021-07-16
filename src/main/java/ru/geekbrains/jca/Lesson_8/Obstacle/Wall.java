package ru.geekbrains.jca.Lesson_8.Obstacle;

/** Класс стены, разновидность препятствия */
public class Wall extends Obstacle {

    private int height;         // высота стены

    public Wall(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}
