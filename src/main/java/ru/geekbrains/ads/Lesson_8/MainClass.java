package ru.geekbrains.ads.Lesson_8;

/**
 * 1. Создать программу, реализующую Хеш-таблицу методом цепочек.
 **/

public class MainClass {

    public static void main(String[] args) {

        HashTable<Product, Integer> hashTable = new HashTableImpl<>(5);//5 * 2 = 10

        hashTable.put(new Product(1, "Orange"), 150);
        hashTable.put(new Product(77, "Banana"), 100);
        hashTable.put(new Product(67, "Carrot"), 228);
        hashTable.put(new Product(64, "Lemon"), 250);
        hashTable.put(new Product(51, "Milk"), 120);
        hashTable.put(new Product(21, "Potato"), 67);

        System.out.println("Size is " + hashTable.size());
        hashTable.display();

        System.out.println("Cost potato is " + hashTable.get(new Product(21, "Potato")));
        System.out.println("Cost banana is " + hashTable.get(new Product(77, "Banana")));
        System.out.println("Cost carrot is " + hashTable.get(new Product(67, "Carrot")));

        System.out.println("Removing Potato and Banana...");
        System.out.println(hashTable.remove(new Product(21, "Potato")));
        System.out.println(hashTable.remove(new Product(77, "Banana")));


        System.out.println("Cost potato is " + hashTable.get(new Product(21, "Potato")));
        System.out.println("Cost banana is " + hashTable.get(new Product(77, "Banana")));
        System.out.println("Cost carrot is " + hashTable.get(new Product(67, "Carrot")));

        hashTable.put(new Product(47, "Pineapple"), 228);

        hashTable.display();
        System.out.println("Size is " + hashTable.size());
    }
}