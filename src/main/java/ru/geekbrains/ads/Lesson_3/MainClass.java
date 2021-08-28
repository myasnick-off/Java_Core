package ru.geekbrains.ads.Lesson_3;

public class MainClass {
    public static void main(String[] args) {

        missingValueSearchTesting();
        dequeTesting();
    }

    // метод для проверки 1-го задания
    public static void missingValueSearchTesting() {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16};   // 11
        int[] arr2 = {1, 2, 4, 5, 6};                                       // 3
        int[] arr3 = {};                                                    // 1

        Searcher searcher = new Searcher();

        System.out.println("Missing: " + searcher.missingValueSearch(arr1));
        System.out.println("Missing: " + searcher.missingValueSearch(arr2));
        System.out.println("Missing: " + searcher.missingValueSearch(arr3));
        System.out.println("---------------------------------------------");
    }

    // метод для проверки 2-го задания
    public static void dequeTesting() {
        DequeImpl<Integer> deque = new DequeImpl<>(5);

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
}

