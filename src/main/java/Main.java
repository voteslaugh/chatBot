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
                if (Objects.equals(input, "/set")){
                    //задаем диапаон чисел
                    System.out.println("Введите диапазон чисел");
                    System.out.println("Минимальное число: "); //надо проверить что это числа
                    long min = scanner.nextLong();
                    System.out.println("Максимальное число: ");
                    long max = scanner.nextLong();
                    bot.setGenerator(min, max);
                    question = bot.getQuestion();
                }

            }
        } while (!Objects.equals(input, "/exit"));
        System.out.println("Бот завершил работу");
    }
}
