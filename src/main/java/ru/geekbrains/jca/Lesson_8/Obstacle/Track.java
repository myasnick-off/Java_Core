package ru.geekbrains.jca.Lesson_8.Obstacle;

/** Класс беговой дорожки, разновидность препятствия */
public class Track extends Obstacle {

    private int length;         // длина беговой дорожки

    public Track(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
