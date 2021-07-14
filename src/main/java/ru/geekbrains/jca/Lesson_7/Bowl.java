package ru.geekbrains.jca.Lesson_7;

/** класс миски */
public class Bowl {

    private boolean isEmpty;            // флаг наличия еды в миске
    private int foodVolume;             // текущее количество еды в миске
    private int maxVolume;              // предельно допустимый объем миски

    public Bowl(int maxVolume) {        // в конструкторе создается пустая миска с заданным объемом
        this.maxVolume = maxVolume;
        this.isEmpty = true;
    }

    /** геттер для флага isEmpty */
    public boolean isEmpty() {
        return isEmpty;
    }

    /** метод наполнения миски
     * foodVolume - количество помещаемой еды в миску */
    public void fill(int foodVolume) {
        if ((this.foodVolume += foodVolume) > maxVolume) {  // если общее количество еды превышает максимальный объем:
            this.foodVolume = maxVolume;                    // то помещаем в миску максимальный объем еды
            System.out.println("Too much food!");
        }
        if (this.foodVolume > 0) isEmpty = false;           // если в итоге в миске есть еда, то сбрасываем флаг isEmpty
        checkFood();                                        // запускаем метод проврки содержимого миски
    }

    /** метод уменьшения еды из миски
     * возвращает уровень голода кота, который решил поесть из миски
     * appetite - уровень аппетита кота, который решил поесть из миски */
    public int decrease(int appetite) {
        int hungryVal = 0;                          // по умолчанию уровень голода кота равен нулю
        foodVolume -= appetite;                     // вычитаем из еды в миске количество, соответствующее аппетиту кота
        if (foodVolume < 0) {                       // если еды не хватило (остаток отрицательный)
            hungryVal = - foodVolume;               // недостающее количество присваиваем уровню голода кота
            foodVolume = 0;                         // обнуляем количество еды в миске
        }
        if (this.foodVolume == 0) isEmpty = true;   // если еды в миске не осталось, устанавливем флаг isEmpty
        return hungryVal;                           // возвращаем уровень голода кота
    }

    /** метод проврки содержимого миски */
    public void checkFood() {
        if (isEmpty)
            System.out.println("The bowl is empty!");
        else
            System.out.printf("There are %d peaces of food in the bowl.\n\n", foodVolume);
    }
}
