package ru.geekbrains.ads.Lesson_5;

/**
 * Класс предмета, помещаемого в рюкзак
 **/
public class Item {
    private String name;        // название предмета
    private int cost;           // стоимость предмета
    private int weight;         // вес предмета


    public Item(String name, int cost, int weight) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return  name +
                "(cost=" + cost +
                ", weight=" + weight + ")";
    }
}
