package ru.geekbrains.jca.Lesson_6;

public class Main {

    public static void main(String[] args) {

        //создаем массив животных
        Animal[] animals = {
                new Cat("Snowball"),
                new Dog("Sparky"),
                new Cat("Tom"),
                new Dog("Lucky")
        };

        // в цикле инициирем действия животных из массива animals
        for (Animal animal : animals) {
            // инициируем бег животного на разные дистанции
            animal.run(100);
            animal.run(400);
            animal.run(700);
            //если животное - собака
            if (animal instanceof Dog) {
                //инициируем плавание животного на разные дистанции
                ((Dog) animal).swim(10);
                ((Dog) animal).swim(20);
            }
            System.out.println();
        }
    }
}
