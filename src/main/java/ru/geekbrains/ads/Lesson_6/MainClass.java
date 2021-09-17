package ru.geekbrains.ads.Lesson_6;

/**
 * 1. Создать и запустить программу для построения двоичного дерева.
 * В цикле построить двадцать деревьев с глубиной в 4 уровней.
 * Данные, которыми необходимо заполнить узлы деревьев, представляются в виде чисел типа int.
 * Число, которое попадает в узел, должно генерироваться случайным образом в диапазоне от -25 до 25.
 * 2. Проанализировать, какой процент созданных деревьев являются несбалансированными.
 **/

public class MainClass {

    private final static int NUMBER_OF_TREES = 20;
    private final static int MAX_LEVELS = 4;

    public static void main(String[] args) {

        treeBalanceAnalise();
    }


    // Метод для генерации двичного дерева с заданным количеством уровней
    public static Tree<Integer> treeGenerator(int maxLevel) {
        Tree<Integer> intTree = new TreeImpl<>(maxLevel);
        int value;                                                      // значение, помещаемое в узел дерева

        do {
            do {
                value = (int) (Math.round(Math.random() * 50) - 25);    // генерируем случайное гначение от -25 до 25
            }
            while (intTree.contains(value) && intTree.size() < 51);     // пока не получим уникальное, еще отсуствующее в деревеб или не переберем все значения в диапазоне
        }
        while (intTree.add(value));         // заполняем дерево, пока не привысим ограничение по количеству уровней

        intTree.display();
        System.out.println("Tree balance result: " + intTree.isTreeBalanced());
        System.out.println();
        return intTree;
    }

    // Метод для вычисления процента несбалансированных деревьев и выборки в NUMBER_OF_TREES деревьев
    public static void treeBalanceAnalise() {
        int nonBalancedCounter = 0;                             // счетчик несбалансированных деревьев

        for (int i = 0; i < NUMBER_OF_TREES; i++) {             // генерируем NUMBER_OF_TREES деревьев
            if (!treeGenerator(MAX_LEVELS).isTreeBalanced()) {  // если дерево несбалансировано
                nonBalancedCounter++;                           // инкрементируем счетчик
            }
        }
        int partOfNonBalanced = nonBalancedCounter * 100 / NUMBER_OF_TREES; // вычисляем процент несбалансированных деревьев
        System.out.println("The part of not balanced trees is: " + partOfNonBalanced + "%");
    }
}
