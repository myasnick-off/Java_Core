package ru.geekbrains.ads.Lesson_3;

/**
 * 2. Создать класс для реализации Deque (двусторонней очереди).
 */

public class DequeImpl<E> implements Deque<E> {

    private final E[] data;     // массив двусторонней очереди
    private int leftEnd;        // индекс левого конца очереди
    private int rightEnd;       // индекс правого конца очереди
    private int size;           // размер очереди

    // Конструктор создает пустую очердь с масимальным ее размером в качестве передаваемого параметра
    public DequeImpl(int maxSize) {
        this.data = (E[]) new Object[maxSize];
        this.leftEnd = maxSize / 2;             // определяем индекс левого конца очереди посередине массива
        this.rightEnd = this.leftEnd - 1;       // определяем индекс правого конца очереди посередине массива со смещением на 1 влево
        this.size = 0;
    }

    // Метод вставки значения с левой стороны очереди
    @Override
    public boolean insertLeft(E value) {
        if (isFull()) {
            return false;
        }
        if (leftEnd == 0) {                 // если слева больше нет места,
            shiftRight();                   // смещаем значения в массиве на одну позицию вправо
        }
        data[--leftEnd] = value;
        size++;
        return true;
    }

    // Метод вставки значения с правой стороны очереди
    @Override
    public boolean insertRight(E value) {
        if (isFull()) {
            return false;
        }
        if (rightEnd == data.length-1) {    // если справа больше нет места,
            shiftLeft();                    // смещаем значения в массиве на одну позицию влево
        }
        data[++rightEnd] = value;
        size++;
        return true;
    }

    // Метод для смещения всех значений массива очереди на одну позицию вправо
    private void shiftRight() {
        for (int i = rightEnd; i >= leftEnd; i--) {
            data[i+1] = data[i];
        }
        rightEnd++;
        leftEnd++;
    }

    // Метод для смещения всех значений массива очереди на одну позицию влево
    private void shiftLeft() {
        for (int i = leftEnd; i <= rightEnd; i++) {
            data[i-1] = data[i];
        }
        rightEnd--;
        leftEnd--;
    }

    @Override
    public E removeLeft() {
        if (isEmpty()) {
            return null;
        }
        E value = data[leftEnd++];
        size--;
        return value;
    }

    @Override
    public E removeRight() {
        if (isEmpty()) {
            return null;
        }
        E value = data[rightEnd--];
        size--;
        return value;
    }

    @Override
    public boolean insert(E value) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E peekFront() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[ ");
        for (int i = leftEnd; i <= rightEnd; i++) {
            string.append(data[i]);
            if (i != rightEnd) {
                string.append(", ");
            }
        }
        string.append(" ]");
        return string.toString();
    }
}
