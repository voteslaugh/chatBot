import bot.Bot;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static boolean isNumber(String s) throws NumberFormatException { //проверка строки на число
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String [] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input, question = null;
        Bot bot = new Bot();
        boolean flag = false;
        do {
            if (!flag) {
                System.out.println("Для начала введите /start");
            }
            if (flag) {
                System.out.println(question);
            }
            input = scanner.next();
            if (!flag){
                if (Objects.equals(input, "/start"))
                {
                    flag = true;
                    question = bot.getQuestion();
                    System.out.println("""
                            Привет, я mathBot
                            Я помогу тебе с математикой
                            Я буду отправлять тебе математисеские примеры, а ты должен их правильно решать
                            Поддерживаемые операции: сложение, вычитание, умножение, деление
                            Также я могу создать для тебя файлик с примерами.
                            Удачи в изучении математики!""");
                }
            } else {
                if (isNumber(input)) {
                    if (bot.checkAnswer(Long.parseLong(input))) {
                        question = bot.getQuestion();
                        System.out.println("Молодец,правильно");
                    } else {
                        System.out.println("Попробуй еще раз");
                    }
                }
                long min, max;
                switch(input) {
                    case("/set"):
                        System.out.println("Введите диапазон чисел");
                        System.out.println("Минимальное число: ");
                        min = scanner.nextLong();
                        System.out.println("Максимальное число: ");
                        max = scanner.nextLong();
                        bot.setGeneratorMin(min);
                        bot.setGeneratorMax(max);
                        question = bot.getQuestion();
                        break;
                    case("/setmin"):
                        System.out.println("Введие минимальное число: ");
                        min = scanner.nextLong();
                        bot.setGeneratorMin(min);
                        question = bot.getQuestion();
                        break;
                    case("/setmax"):
                        System.out.println("Введие максимальное число: ");
                        max = scanner.nextLong();
                        bot.setGeneratorMin(max);
                        question = bot.getQuestion();
                        break;
                    case("/setoperation"):
                        System.out.println("Введие операцию: ");
                        scanner.nextLine();
                        String operation = scanner.nextLine();
                        bot.setGeneratorOperation(operation);
                        question = bot.getQuestion();
                        break;
                }

            }
        } while (!Objects.equals(input, "/exit"));
        System.out.println("Бот завершил работу");
    }
}
