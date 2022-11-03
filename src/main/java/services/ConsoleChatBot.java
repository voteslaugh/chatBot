package services;

import bot.Bot;
import bot.BotReply;
import bot.ChatUpdate;

import java.util.Scanner;

public class ConsoleChatBot {
    private final Bot BOT;
    private final Scanner scanner = new Scanner(System.in);
    public ConsoleChatBot(Bot BOT) {
        this.BOT = BOT;
    }

    public void run() {
        while(true) {
            String text = this.scanner.nextLine();
            ChatUpdate chatUpdate = new ChatUpdate("123", "123");
            chatUpdate.setText(text);
            BotReply botReply = this.BOT.reply(chatUpdate);
            System.out.println(botReply.getText());
        }
    }
}
