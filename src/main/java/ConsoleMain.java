import bot.configs.FunctionConfig;
import components.TaskGenerator;
import services.ConsoleChatBot;
import components.DataBase;
import bot.Bot;
import components.MathBot;

public class ConsoleMain {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        TaskGenerator taskGenerator = new TaskGenerator();
        FunctionConfig functionConfig = new FunctionConfig(taskGenerator);
        Bot mathBot = new MathBot(functionConfig, dataBase);
        ConsoleChatBot console = new ConsoleChatBot(mathBot);
        console.run();
    }
}
