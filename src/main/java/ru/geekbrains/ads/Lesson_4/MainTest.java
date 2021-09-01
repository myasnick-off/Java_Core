package ru.geekbrains.ads.Lesson_4;


import java.util.ArrayList;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        dequeTest();
        linkedListIteratorTest();

        List<Integer> list= new ArrayList<>();
    }

    public static void dequeTest() {
        LinkedDequeImpl<Integer> deque = new LinkedDequeImpl<>();

        deque.insertLeft(4);
        deque.display();
        deque.insertRight(5);
        deque.display();
        deque.insertRight(6);
        deque.display();
        deque.insertLeft(3);
        deque.display();
        deque.insertLeft(2);
        deque.display();
        deque.insertLeft(1);
        deque.display();
        deque.insertRight(7);
        deque.display();
        System.out.println("Size: " + deque.size());

        System.out.println("Removed from left: " + deque.removeLeft());
        deque.display();
        System.out.println("Size: " + deque.size());

        System.out.println("Removed from right: " + deque.removeRight());
        deque.display();
        System.out.println("Size: " + deque.size());
    }

    // Тестовый метод для проверки работы итератора в LinkedList согласно заданию №1
    public static void linkedListIteratorTest() {
        LinkedList<Integer> linkedList = new SimpleLinkedListImpl<>();
        linkedList.insertFirst(1);
        linkedList.insertFirst(2);
        linkedList.insertFirst(3);
        linkedList.insertFirst(4);
        linkedList.insertFirst(5);
        linkedList.insertFirst(6);

        for (Integer element : linkedList) {
            System.out.println("element: " + element);
        }
    }
}
