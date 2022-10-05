package bot;

import java.util.Scanner;

public class Console extends Program {
    private final Scanner scanner = new Scanner(System.in);
    public void run() {
        while (true) {
            String message = this.scanner.nextLine();
            BotReply botReply = this.mathBot.reply(new ChatUpdate("default", message));
            switch (mathBot.getStatus()) {
                case SLEEPING:
                    System.out.print(botReply.getMessage());
                    break;
                case WAITING_COMMAND:
                    System.out.print(botReply.getMessage());
                    break;
                case TESTING_FOR_ONE:
                    System.out.print(botReply.getMessage());
                    System.out.print(botReply.getTask().getQuestion());
                    break;
            }
        }
    }
}
