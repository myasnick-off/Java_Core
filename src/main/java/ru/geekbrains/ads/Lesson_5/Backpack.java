package ru.geekbrains.ads.Lesson_5;

import java.util.ArrayList;
import java.util.List;

/**
 * 2. Написать программу «Задача о рюкзаке» с помощью рекурсии.
 **/

public class Backpack {

    private int maxWeight;              // максимальный вес рюкзака
    private int maxCost;                // маскимальная суммарная стоимость набора вещей
    private List<Item> bestSet;         // наиболее оптимальный набор вещей

    // в конструкторе в качестве параметра передаем максимальный вес рюкзака
    public Backpack(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    // геттер для максимальной суммарной стоимости
    public int getMaxCost() {
        return maxCost;
    }

    // метод поиска оптимального набора предметов с учетом общего веса и стоимости
    public List<Item> pack(List<Item> itemList) {
        if (itemList.size() > 0) {
            compareSet(itemList);
            for (int i = 0; i < itemList.size(); i++) {
                List<Item> cutList = new ArrayList<>(itemList);
                cutList.remove(i);
                pack(cutList);
            }
        }
        return bestSet;
    }

    // метод сравнения набора предметов, передаваемого в качестве параметра,
    // с текущими лучшими критериями веса и стоимости
    private void compareSet(List<Item> itemList) {
        int totalWeight = findTotalWeight(itemList);
        int totalCost = findTotalCost(itemList);
        if (totalWeight <= maxWeight) {
            if (totalCost > maxCost) {
                maxCost = totalCost;
                bestSet = itemList;
            }
        }
    }

    // метод вычисления суммарного веса набора предметов, передаваемого в качестве параметра
    private int findTotalWeight(List<Item> itemList) {
        int weight = 0;
        for (Item item : itemList) {
            weight += item.getWeight();
        }
        return weight;
    }

    // метод вычисления суммарной стоимости набора предметов, передаваемого в качестве параметра
    private int findTotalCost(List<Item> itemList) {
        int cost = 0;
        for (Item item : itemList) {
            cost += item.getCost();
        }
        return cost;
    }

    @Override
    public String toString() {
        return bestSet + "\n" +
                "max cost = " + maxCost;
    }
}
