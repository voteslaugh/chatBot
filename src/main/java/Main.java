import bot.Bot;
import bot.Commands;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Bot bot = new Bot();
        do {
            input = scanner.next();
            if (Objects.equals(input, "/start"))
                bot.checkCommand(Commands.START);
        } while (!Objects.equals(input, "/exit"));
    }
}
