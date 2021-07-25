package ru.geekbrains.jca.Lesson_6;

public class Animal {

    private String type;        // вид животного
    private String name;        // кличка животного

    public Animal(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    // метод, описывающий бег животного
    public void run(int distance) {
        System.out.printf("%s %s had run %d meters.\n", this.type, this.name, distance);
    }

    // метод, предупреждающий о превышении возможной дистанции для животного
    public void distanceWarning() {
        System.out.printf("Sorry! Too large distance for %s.\n", this.type);
    }
}
