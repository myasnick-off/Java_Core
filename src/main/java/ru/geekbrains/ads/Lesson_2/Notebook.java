package ru.geekbrains.ads.Lesson_2;

public class Notebook implements Comparable<Notebook> {

    // Статический массив названий брендов ноутбуков
    public static final String[] nameList = {"Lenuvo", "Asos", "MacNote", "Eser", "Xamiou"};

    private int price;  // цена
    private int RAM;    // количество оперативной памяти
    private int name;   // порядковый номер в массиве брендов, соответствующий бренду данного ноутбука

    // в конструкторе генерируем ноутбук со случайными параметрами
    public Notebook() {
        this.price = 100 * ((int) (Math.random() * 6) + 5);
        this.RAM = 4 * ((int) (Math.random() * 3) + 1);
        this.name = (int) (Math.random() * nameList.length);
    }

    @Override
    public String toString() {
        return nameList[name] + ", " + RAM + "Gb RAM" + ": " + price + "$";
    }

    @Override
    public int compareTo(Notebook o) {
        int result;
        result = Integer.compare(this.price, o.price);
        if (result == 0) {
            result = Integer.compare(this.RAM, o.RAM);
            if (result == 0) {
                result = Integer.compare(this.name, o.name);
            }
        }
        return result;
    }
}
