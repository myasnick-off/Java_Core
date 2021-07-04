package ru.geekbrains.jca.Lesson_3;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class WordGame {

    // массив со словами для загадывания
    private static final String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
            "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
            "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

    private static boolean isGameStopped;   // флаг остановки игры
    private static String secretWord;       // загаданное слово
    private static int attempts;            // количество попыток
    private static Scanner scanner;         // сканер консоли

    public static void main(String[] args) {

        WordGame wordGame = new WordGame();         // создаем и инициализируем новый объект игры
        scanner = new Scanner(System.in);           // инициализируем сканер консоли
        wordGame.createGame();                      // создаем игру

        while (!isGameStopped) {                    // в цикле обрабатываем события игры
            String userWord = scanner.next();       // считываем новый вариант слова, введеный игроком
            if (userWord.equals(secretWord)) {      // если вариант игрока совпадает с загаданным словом:
                wordGame.win();                     // то победа!
            }
            else {                                  // иначе:
                wordGame.charsCompare(userWord);    // проводим сравнение букв предложенного слова с буквами загаданного слова
                wordGame.checkAttempts();           // проверяем оставшиеся попытки
            }
            if (isGameStopped) wordGame.restartRequest(); // если игра остановлена, запрашиваем рестарт
        }
        scanner.close();

    }

    /* метод создания новой игры */
    public void createGame() {
        isGameStopped = false;                              // сбрасываем флаг остановки игры
        attempts = 5;                                       // инициализируем количество попыток
        Random random = new Random();                       // создаем объект random
        secretWord = words[random.nextInt(words.length)];   // выбираем случайное слово из массива (загадываем слово)
        //System.out.println(secretWord);                   // печать загаданного слова для проверки
        System.out.println("Загадано одно из слов:");
        System.out.println(Arrays.toString(words));         // выводим весь массив слов на экран
        System.out.println();
        System.out.println("Попробуй угадать его! У тебя " + attempts + " попыток." );

    }

    /* метод сравнения букв предложенного слова с буквами загаданного слова*/
    public void charsCompare(String userWord) {
        StringBuilder result = new StringBuilder();                         // создаем новый объект StringBuilder для последующей сборки строки
        int counter = 0;                                                    // создаем счетчик угаданных букв и инициализируем его нулем
        //int wordLength = Math.min(secretWord.length(), userWord.length());  // определяем длину самого короткого слова из предложенного или загаданного
        for (int i = 0; i < secretWord.length() && i < userWord.length(); i++) { // пробегаемся по буквам предложенного слова (ограничение по длине короткого слова )
            char ch = userWord.charAt(i);
            if (ch == secretWord.charAt(i)) {                               // если буквы совпали:
                result.append(ch);                                          // добавляем эту букву в нашу сборку строки
                counter++;                                                  // инкрементируем счетчик угаданных букв
            }
            else result.append('#');                                        // иначе добавляем в нашу сборку строки символ #
        }
        result.append("###############");                                   // дозаполняем сборку символами # с запасом
        result.setLength(15);                                               // обрезаем сборку до 15 символов
        System.out.println("Угадана(-ы) " + counter + " буква(-ы) слова:");
        System.out.println(result);                                         // выводим на экран получившуюся сборку
    }

    /* матод декрементации и проверки оставшихся попыток */
    public void checkAttempts() {
        attempts--;
        if (attempts > 0) {
            System.out.println("У тебя осталось " + attempts + " попытки(-ок):");
        }
        else gameOver();
    }

    /* метод инициации проигрыша */
    public void gameOver() {
        isGameStopped= true;
        System.out.println("Ты проиграл!!!");
        System.out.println("---------------------------------------------------");
    }

    /* метод инициации выигрыша */
    public void win() {
        isGameStopped = true;
        System.out.println("ПОЗДРАВЛЯЮ!!! Ты угадал!");
        System.out.println("---------------------------------------------------");
    }

    /* метод инициации и обработки запроса на рестарт игры */
    public void restartRequest() {
        System.out.println();
        System.out.println("Повторим? (yes/no)");
        if (scanner.next().equals("yes")) {
            isGameStopped = false;
            System.out.println();
            createGame();
        }
    }
}
