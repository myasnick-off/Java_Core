package ru.geekbrains.jca.Lesson_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс коробки для фруктов
 *      fields:     fruits - список фруктов типа Т, лежащие в коробке
 * */
public class Box<T extends Fruit> {

    private List<T> fruits;

    // конструктор без параметров
    public Box() {
        fruits = new ArrayList<>();
    }

    /** Метод добавляет один фрукт типа Т в коробку
     * */
    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    /** Метод добавляет сразу несколько фруктов типа Т в коробку
     * */
    public void addFruits(T... fruits) {
        this.fruits.addAll(Arrays.asList(fruits));
    }

    /** Метод определяет общий вес коробки с фруктами
     * return:      общий вес коробки
     * */
    public float getWeight() {
        if (fruits.isEmpty()) return 0.0f;                  // если коробка пуста возварщаем 0
        return fruits.size() * fruits.get(0).getWeight();   // вычисляем общий вес, зная количество и вес одного фрукта
    }

    /** Метод сравнивает текущую и переданную коробки по весу
     * param:      box - другая коробка с любыми фруктами
     * return:     true если коробки равны (в пределах погрешности), иначе false
     * */
    public boolean compare(Box<?> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0.001;
    }

    /** Метод перекладывает все фрукты из текущей коробки в другую
     * * param:     anotherBox - другая коробка с фруктами
     * */
    public void pourInto(Box<? super T> anotherBox) {
        if (!fruits.isEmpty()) {                        // если текущая коробка не пуста
            for (T fruit : fruits) {                    // в цикле добавляем все фрукты из текущей коробки в другую
                anotherBox.addFruit(fruit);
            }
            fruits.clear();                             // удаляем все фрукты из текущей коробки
        }
    }

    @Override
    public String toString() {
        if (fruits.isEmpty()) return "The box is empty!";
        return String.format("The box contains: %d %ss",
                fruits.size(),
                fruits.get(0).getClass().getSimpleName());
    }
}
