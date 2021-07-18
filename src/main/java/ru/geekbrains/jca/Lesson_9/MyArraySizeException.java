package ru.geekbrains.jca.Lesson_9;

public class MyArraySizeException extends Exception {

    public MyArraySizeException(int arraySize) {
        super(String.format("Array size is another then %dx%d!", arraySize, arraySize));
    }
}
