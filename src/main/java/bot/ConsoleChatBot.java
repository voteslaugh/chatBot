package bot;

import java.util.Scanner;

public class ConsoleChatBot extends ChatBot {
    private final Scanner scanner = new Scanner(System.in);
    public void run() {
        while (true) {
            String text = this.scanner.nextLine();
            ChatUpdate chatUpdate = new ChatUpdate("123", "123");
            chatUpdate.setText(text);
            BotReply botReply = this.mathBot.reply(chatUpdate);
            System.out.println(botReply.getText());
        }
    }
}
