package ru.geekbrains.jca.Lesson_6;

public class Cat extends Animal {

    public Cat(String name) {
        super("Cat", name);
    }

    @Override
    public void run(int distance) {
        if (distance <= 200) super.run(distance);
        else distanceWarning();
    }
}
