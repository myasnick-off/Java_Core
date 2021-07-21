package ru.geekbrains.jca.Lesson_9;

public class MyArrayDataException extends Exception {

    public MyArrayDataException(int rowPosition, int columnPosition) {
        super(String.format("Array element in position [%d][%d] is not a number!", rowPosition, columnPosition));
    }
}
