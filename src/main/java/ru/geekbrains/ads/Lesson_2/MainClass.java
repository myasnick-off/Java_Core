package ru.geekbrains.ads.Lesson_2;

public class MainClass {

    public static final int NOTEBOOK_COUNT = 5000;  // количество ноутбуков массиве
    public static long time;                        // время выполнения сортировки

    public static void main(String[] args) {

        Sorter<Notebook> sorter = new Sorter<>();       // создаем объект сортировщика
        Notebook[] notebooks;                           // объявляем массив ноутбуков

        notebooks = getNotebookArray(NOTEBOOK_COUNT);   // генерируем массив ноутбуков
        startTimer();                                   // запускаем таймер
        sorter.selectionSort(notebooks);                // применяем для массива один из типов сортировки
        stopTimer("Selection Sort");            // останавливаем таймер и выводим его значение на экран
//        printArray(notebooks);

        notebooks = getNotebookArray(NOTEBOOK_COUNT);
        startTimer();
        sorter.insertionSort(notebooks);
        stopTimer("Insertion Sort");
//        printArray(notebooks);

        notebooks = getNotebookArray(NOTEBOOK_COUNT);
        startTimer();
        sorter.bubbleSort(notebooks);
        stopTimer("Bubble Sort");
//        printArray(notebooks);

        notebooks = getNotebookArray(NOTEBOOK_COUNT);
        startTimer();
        sorter.cocktailSort(notebooks);
        stopTimer("Cocktail Sort");
//        printArray(notebooks);
    }

    // метод заполнения массива ноутбуков
    public static Notebook[] getNotebookArray(int length) {
        Notebook[] notebooks = new Notebook[length];
        for (int i = 0; i < length; i++) {
            notebooks[i] = new Notebook();
        }
        return notebooks;
    }

    // метод вывода массива на экран
    public static <E> void printArray(E[] arr) {
        System.out.println("-----------------------------------------");
        for (E element : arr) {
            System.out.println(element.toString());
        }
    }

    // метод запуска таймера
    public static void startTimer() {
        time = System.currentTimeMillis();
    }

    // метод остановки таймера и вывода значения на экран
    public static void stopTimer(String sortType) {
        time = System.currentTimeMillis() - time;
        System.out.printf("%s >>>>>>>> Total time: %d ms\n", sortType, time);
    }

}
