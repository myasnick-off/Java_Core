package ru.geekbrains.jca.Lesson_13;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс гоночной трассы
 * */

public class Race {
    private ArrayList<Stage> stages;     // массив с этапами гоночной трассы

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
