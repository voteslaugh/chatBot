package bot;

import java.util.Scanner;

public class Console extends Program {
    private final Scanner scanner = new Scanner(System.in);
    public void run() {
        while (true) {
            String message = this.scanner.nextLine();
            ChatUpdate chatUpdate = new ChatUpdate(null, null);
            chatUpdate.setText(message);
            BotReply botReply = this.mathBot.reply(chatUpdate);
            switch (botReply.getStatus()) {
                case SLEEPING:
                    System.out.print(botReply.getMessage().getText());
                    break;
                case WAITING_COMMAND:
                    System.out.print(botReply.getMessage().getText());
                    break;
                case TESTING_FOR_ONE:
                    System.out.print(botReply.getMessage().getText());
                    System.out.print(botReply.getMessage().getTask().getQuestion());
                    break;
            }
        }
    }
    protected ChatUpdate getChatUpdate() {
        return null;
    }
}
