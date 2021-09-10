package ru.geekbrains.ads.Lesson_6;

/**
 * Интерфейс, реализованный на вебинаре
 **/
public interface Tree<E extends Comparable<? super E>> {

    enum TraversMode {
        IN_ORDER, PRE_ORDER, POST_ORDER
    }

    boolean isTreeBalanced();       // для реализации домашнего задания

    boolean add(E value);

    boolean contains(E value);

    boolean remove(E value);

    boolean isEmpty();

    int size();

    void display();

    void traverse(TraversMode mode);
}