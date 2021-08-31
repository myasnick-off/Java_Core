package ru.geekbrains.ads.Lesson_4;

import ru.geekbrains.ads.Lesson_3.Deque;

/**
 * Задание №2
 * Реализовать Deque на основе двунаправленного списка
 * */
public class LinkedDequeImpl<E> implements Deque<E> {

    private final TwoSideLinkedList<E> data;

    public LinkedDequeImpl() {
        this.data = new  TwoSideLinkedListImpl<>();
    }

    @Override
    public boolean insertLeft(E value) {
        data.insertFirst(value);
        return true;
    }

    @Override
    public boolean insertRight(E value) {
        data.insertLast(value);
        return true;
    }

    @Override
    public E removeLeft() {
        return data.removeFirst();
    }

    @Override
    public E removeRight() {
        E lastElement = data.getLast();
        data.remove(lastElement);
        return lastElement;
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
        return data.getFirst();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void display() {
        data.display();
    }

    @Override
    public String toString() {
        return "LinkedDequeImpl{" + "data=" + data + '}';
    }
}
