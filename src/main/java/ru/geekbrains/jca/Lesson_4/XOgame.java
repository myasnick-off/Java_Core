package ru.geekbrains.jca.Lesson_4;

import java.util.Random;
import java.util.Scanner;

public class XOgame {

    private static final char PLAYER_DOT = 'X';                 // символ игрока
    private static final char AI_DOT = 'O';                     // символ компа
    private static final char EMPTY_DOT = '.';                  // символ пустой клетки

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;                              // матрица символов на игровом поле
    private static int width;                                   // ширина игрового поля
    private static int height;                                  // высота игрового поля
    private static int winLength;                               // длина ряда для победы
    private static int playerScore;                             // очки игрока
    private static int aiScore;                                 // очки компа
    private static boolean isGameStopped;                       // флаг остановки игры

    public static void main(String[] args) {
        initGame();                                          // инициализируем настройки игры
        while (!isGameStopped) {                             // в цикле пока игра не остановлена:
            initField();                                        // инициализируем игровое поле
            playRound();                                        // запускаем игровой раунд
            printResults();                                     // печатаем результаты раунда
            restartRequest();                                   // запрашиваем новый раунд
        }
    }

    /** метод инициализации настроек игры */
    public static void initGame() {
        System.out.println("Welcome to tick-tack-toe game!");
        System.out.print("Please, choose game field dimensions width and height split with whitespace >>> ");
        width = scanner.nextInt();
        height = scanner.nextInt();
        System.out.print("Please, choose length of DOTs for winning >>> ");
        winLength = scanner.nextInt();
    }

    /** метод инициализации игрового поля */
    private static void initField() {
        isGameStopped = false;                  // сбрасываем флаг остановки игры
        field = new char[height][width];        // инициализируем матрицу элементов игрового поля
        for (int y = 0; y < height; y++) {      // заполняем матрицу пустыми клетками
            for (int x = 0; x < width; x++) field[y][x] = EMPTY_DOT;
        }
        printField();                           // выводим игровое поле на экран
    }

    /** метод печати игрового поля */
    private static void printField() {
        System.out.print(" ");
        for (int i = 0; i < width * 2 + 1; i++) System.out.print(i % 2 == 0 ? "_" : i / 2 + 1);
        System.out.println();

        for (int i = 0; i < height; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < width; j++) System.out.print(field[i][j] + "|");
            System.out.println();
        }
        for (int i = 0; i <= width * 2 + 1; i++) System.out.print("-");
        System.out.println();
    }

    /** метод запуска игрового раунда */
    private static void playRound() {
        while (true) {
            playerTurn();                           // делает ход игрок
            printField();                           // печатается игровое поле
            if (checkGameState(PLAYER_DOT)) break;  // проверка состояния игры, если игра окончена, прерываем ее
            aiTurn();                               // делает ход комп
            printField();                           // печатается игровое поле
            if (checkGameState(AI_DOT)) break;      // проверка состояния игры, если игра окончена, прерываем ее
        }
    }

    /** метод печати результатов игры */
    private static void printResults() {
        System.out.printf("SCORE IS: PLAYER   AI\n            %d      %d\n", playerScore, aiScore);
    }

    /** метод запроса на рестарт игры */
    private static void restartRequest() {
        System.out.print("Wanna play again? Enter Y or N >>> ");
        isGameStopped = !scanner.next().equalsIgnoreCase("y");
    }

    /** метод проверки состояния игрового раунда */
    private static boolean checkGameState(char dot) {
        if (checkDraw()) return true;                   // если ничья, раунд окончен

        if (checkWin(dot)) {
            if (dot == PLAYER_DOT) {                    // если победил игрок
                System.out.println("YOU win!");
                playerScore++;                          // прибавляем очки игроку
            } else {                                    // если победил комп
                System.out.println("AI win!");
                aiScore++;                              // прибавляем очки компу
            }
            return true;
        }
        return false;
    }

    /** метод проверки на ничью */
    private static boolean checkDraw() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isCellEmpty(y, x)) return false;
            }
        }
        System.out.println("Oops... It's DRAW!");
        return true;
    }

    /*
     * ** Сделать проверку на произвольные размеры поля и длину последовательности
     */
    /** метод проверки выигрышной комбинации заданной длины */
    private static boolean checkWin(char dot) {
        // в цикле проходимся по всем клеткам игрового поля
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // инициализируем 4 счетчика символов dot, по одному на каждое направление
                int counter1 = 0;       // для горизонтали вправо
                int counter2 = 0;       // для вертикали вниз
                int counter3 = 0;       // для диагонали вниз
                int counter4 = 0;       // для лиагонали вверх

                // проходим в цикле по всей длине выигрышной комбинации
                for (int i = 0; i < winLength; i++) {
                    if (x + winLength <= width) {               // если длина по горизонтали вправо от текущей ячейки позволяет:
                        if (field[y][x + i] == dot) counter1++; // считаем символы dot по горизонтали вправо
                    }
                    if (y + winLength <= height) {              // если длина по вертикали вниз от текущей ячейки позволяет:
                        if (field[y + i][x] == dot) counter2++; // считаем символы dot по вертикали вниз
                    }
                    if (x + winLength <= width && y + winLength <= height) { // если длина по диагонали вниз от текущей ячейки позволяет:
                        if (field[y + i][x + i] == dot) counter3++;          // считаем символы dot по диагонали вниз
                    }
                    if (x + winLength <= width && y - winLength + 1 >= 0) {  // если длина по диагонали вверх от текущей ячейки позволяет:
                        if (field[y - i][x + i] == dot) counter4++;          // считаем символы dot по диагонали вверх
                    }
                }
                // если количество символов dot хотя бы по одному из направлений совпало с выигышной длиной то возвращаем true
                if (counter1 == winLength || counter2 == winLength || counter3 == winLength || counter4 == winLength) return true;
            }
        }
        return false;
    }

    /** метод поиска оптимального хода для компа
     * возвращает массив с координатами ячейки для оптимального хода компа
     * dot - символ (Х или О) для которого проверяются возможные комбинации
     * rowLength - проверяемая длина последовательности */
    private static int[] guessTurn(char dot, int rowLength) {
        // в цикле проходимся по всем клеткам игрового поля
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                /* алгоритм для направления по горизонтали вправо */
                if (x + rowLength <= width) {                   // если длина по горизонтали вправо от текущей ячейки позволяет:
                    int counter = 0;                            // инициализируем счетчик символов
                    for (int i = 0; i < rowLength; i++) {       // проходим в цикле по всей длине искомой последовательности
                        if (field[y][x + i] == dot) counter++;  // считаем количество символов dot по горизонтали вправо от текущей ячейки
                    }
                    if (counter == rowLength) {                 // если последовательность в данном направлении найдена:
                        if (isCellValid(x + rowLength, y)) {    // если за последовательностью еще есть ячейка
                            if (isCellEmpty(x + rowLength, y))      // если эта ячейка свободна
                                return new int[]{x + rowLength, y};         // возвращаем массив с координатами этой ячейки
                        }                                          // инче:
                        if (isCellValid(x - 1, y)) {            // если перед последовательностью есть еще ячейка
                            if (isCellEmpty(x -1, y))               // если эта ячейка свободна
                                return new int[]{x - 1, y};                // возвращаем массив с координатами этой ячейки
                        }
                    }
                }
                /* алгоритм для направления по вертикали вниз */
                if (y + rowLength <= height) {                  // если длина по вертикали вниз от текущей ячейки позволяет:
                    int counter = 0;                            // инициализируем счетчик символов
                    for (int i = 0; i < rowLength; i++) {       // проходим в цикле по всей длине искомой последовательности
                        if (field[y + i][x] == dot) counter++;  // считаем количество символов dot по вертикали вниз от текущей ячейки
                    }
                    if (counter == rowLength) {                 // если последовательность в данном направлении найдена:
                        if (isCellValid(x, y + rowLength)) {    // если за последовательностью еще есть ячейка
                            if (isCellEmpty(x, y + rowLength))      // если эта ячейка свободна
                                return new int[]{x, y + rowLength};         // возвращаем массив с координатами этой ячейки
                        }                                           // инче:
                        if (isCellValid(x, y - 1)) {             // если перед последовательностью есть еще ячейка
                            if (isCellEmpty(x, y - 1))               // если эта ячейка свободна
                                return new int[]{x, y - 1};                 // возвращаем массив с координатами этой ячейки
                        }
                    }
                }
                /* алгоритм для направления по диагонали вниз */
                if (x + rowLength <= width && y + rowLength <= height) { // если длина по диагонали вниз от текущей ячейки позволяет:
                    int counter = 0;                                     // инициализируем счетчик символов
                    for (int i = 0; i < rowLength; i++) {                // проходим в цикле по всей длине искомой последовательности
                        if (field[y + i][x + i] == dot) counter++;       // считаем количество символов dot по диагонали вниз от текущей ячейки
                    }
                    if (counter == rowLength) {                          // если последовательность в данном направлении найдена:
                        if (isCellValid(x + rowLength, y + rowLength)) { // если за последовательностью еще есть ячейка
                            if (isCellEmpty(x + rowLength, y + rowLength))  // если эта ячейка свободна
                                return new int[]{x + rowLength, y + rowLength};      // возвращаем массив с координатами этой ячейки
                        }                                                      // инче:
                        if (isCellValid(x - 1, y - 1)) {                 // если перед последовательностью есть еще ячейка
                            if (isCellEmpty(x - 1, y - 1))                   // если эта ячейка свободна
                                return new int[]{x - 1, y - 1};                        // возвращаем массив с координатами этой ячейки
                        }
                    }
                }
                /* алгоритм для направления по диагонали ввверх */
                if (x + rowLength <= width && y - rowLength + 1 >= 0) {  // если длина по диагонали вверх от текущей ячейки позволяет:
                    int counter = 0;                                     // инициализируем счетчик символов
                    for (int i = 0; i < rowLength; i++) {                // проходим в цикле по всей длине искомой последовательности
                        if (field[y - i][x + i] == dot) counter++;       // считаем количество символов dot по диагонали вверх от текущей ячейки
                    }
                    if (counter == rowLength) {                          // если последовательность в данном направлении найдена:
                        if (isCellValid(x + rowLength, y - rowLength)) { // если за последовательностью еще есть ячейка
                            if (isCellEmpty(x + rowLength, y - rowLength))  // если эта ячейка свободна
                                return new int[]{x + rowLength, y - rowLength};      // возвращаем массив с координатами этой ячейки
                        }                                                      // инче:
                        if (isCellValid(x - 1, y + 1)) {                 // если перед последовательностью есть еще ячейка
                            if (isCellEmpty(x - 1, y + 1))                  // если эта ячейка свободна
                                return new int[]{x - 1, y + 1};                       // возвращаем массив с координатами этой ячейки
                        }
                    }
                }
            }
        }
        return null;        // если подходящей ячейки не найдено, возвращаем null
    }

    /*
     * *** Сделать более умного ИИ (чтобы ходил хоть как то осмысленно)
     */
    /** метод для совершения хода компом */
    private static void aiTurn() {
        int x, y;
        int[] target = null;                        // массив для хранения координат хода
        for (int i = winLength-1; i > 1 ; i--) {    // в цикле пробегаемся по всем длинам последовательностей от предвыигрышной до самой короткой (из 2-х символов)
            target = guessTurn(PLAYER_DOT, i);      // ищем подходящий ход, чтобы помешать выиграть игроку
            if (target != null) break;              // если такой ход найден, прерываем цикл
            target = guessTurn(AI_DOT, i);          // ищем подходящий ход, чтобы выиграть самому
            if (target != null) break;              // если такой ход найден, прерываем цикл
        }
        if (target != null) {                       // если оптимальную ячейку для хода найти удалось:
            x = target[0];                          // вытаскиваем координаты из массива
            y = target[1];
        }
        else {                                      // в противном случае:
            do {                                    // подбираем случайные координаты пустой ячейки
                x = random.nextInt(width);
                y = random.nextInt(height);
            } while (!isCellEmpty(x, y));
        }
        field[y][x] = AI_DOT;                       // заносим в выбранную ячейку символ компа
    }


    /** метод для совершения хода игроком */
    private static void playerTurn() {
        int x, y;
        boolean isTurnAvailable = false;

        do {
            System.out.print("Please enter coordinates of your turn x & y split with whitespace >>> ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
            if (isCellValid(x, y)) {
                isTurnAvailable = isCellEmpty(x, y);
            }
        } while (!isTurnAvailable);

        field[y][x] = PLAYER_DOT;
    }

    /** метод для проверки существования ячейки */
    private static boolean isCellValid(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    /** метод для проверки пустая ли ячейка */
    private static boolean isCellEmpty(int x, int y) {
        return field[y][x] == EMPTY_DOT;
    }


}
