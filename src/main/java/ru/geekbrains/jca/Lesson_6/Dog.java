package ru.geekbrains.jca.Lesson_6;

public class Dog extends Animal {

    public Dog(String name) {
        super("Dog", name);
    }

    @Override
    public void run(int distance) {
        if (distance <= 500) super.run(distance);
        else distanceWarning();
    }

    public  void swim(int distance) {
        if (distance <= 10)
            System.out.printf("%s %s had swam %d meters.\n", getType(), getName(), distance);
        else distanceWarning();
    }
}
