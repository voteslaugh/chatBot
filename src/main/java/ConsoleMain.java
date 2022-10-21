import bot.ChatBot;
import bot.ConsoleChatBot;
import bot.DataBase;
import bot.Bot;
import bot.MathBot;

public class ConsoleMain {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        Bot mathBot = new MathBot(dataBase);
        ChatBot console = new ConsoleChatBot(mathBot);
        console.run();
    }
}
