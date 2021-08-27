package ru.geekbrains.ads.Lesson_2;

// класс сортировщика массива объектов типа Т
public class Sorter<T extends Comparable<T>> {

    // метод сортировки выбором
    public void selectionSort(T[] arr) {
        int min;
        for (int i = 0; i < arr.length - 1; i++) {
            min = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[min]) < 0) {
                    min = j;
                }
            }
            if (i == min) {
                continue;
            }
            T temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    // метод сортировки вставками
    public void insertionSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T value = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(value) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = value;
        }
    }

    // метод сортировки пузырьком
    public void bubbleSort(T[] arr) {
        boolean isChanged;
        int n = arr.length;
        do {
            isChanged = false;
            n--;
            for (int i = 0; i < n; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    isChanged = true;
                }
            }
        } while (isChanged);
    }

    // метод коктейльной сортировки
    public void cocktailSort(T[] A) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i <= A.length - 2; i++) {
                if (A[i].compareTo(A[i + 1]) > 0) {
                    T temp = A[i];
                    A[i] = A[i + 1];
                    A[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            for (int i = A.length - 2; i >= 0; i--) {
                if (A[i].compareTo(A[i + 1]) > 0) {
                    T temp = A[i];
                    A[i] = A[i + 1];
                    A[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

    }

}
