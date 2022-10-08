import bot.ChatBot;
import bot.ConsoleChatBot;

public class Main {
    public static void main(String [] args) {
        ChatBot console = new ConsoleChatBot();
        console.run();
    }
}
