package ru.geekbrains.ads.Lesson_4;

// Интерфейс, реализованный на вебинаре
public interface TwoSideLinkedList<E> extends LinkedList<E> {

    void insertLast(E value);

    E getLast();

}

