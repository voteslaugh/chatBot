import components.TaskGenerator;
import services.ConsoleChatBot;
import components.DataBase;
import bot.Bot;
import components.MathBot;

public class ConsoleMain {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        TaskGenerator taskGenerator = new TaskGenerator();
        Bot mathBot = new MathBot(taskGenerator, dataBase);
        ConsoleChatBot console = new ConsoleChatBot(mathBot);
        console.run();
    }
}
