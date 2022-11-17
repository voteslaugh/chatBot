import bot.*;
import bot.configs.BotConfig;
import bot.functions.components.Command;
import bot.functions.services.*;
import bot.components.DataBase;
import components.MathBot;
import bot.functions.components.TaskGenerator;
import services.TelegramChatBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramMain {
    public static void main(String[] args) {
        String botName = "name";
        String botToken = "token";

        DataBase dataBase = new DataBase();
        TaskGenerator taskGenerator = new TaskGenerator();

        Function sleep = new Sleep();
        Function waitCommand = new WaitCommand();
        Function bintest = new BinTest(taskGenerator);

        CommandHandler commandHandler = new CommandHandler("/sleep");
        commandHandler.addCommand(new Command(sleep, new FunctionGroup(waitCommand, bintest), "/sleep"));
        commandHandler.addCommand(new Command(waitCommand, new FunctionGroup(sleep), "/start"));
        commandHandler.addCommand(new Command(waitCommand, new FunctionGroup(bintest), "/stop"));
        commandHandler.addCommand(new Command(bintest, new FunctionGroup(waitCommand), "/bintest"));

        BotConfig botConfig = new BotConfig(dataBase, commandHandler);
        Bot mathBot = new MathBot(botConfig);
        TelegramChatBot telegramChatBot = new TelegramChatBot(botName, botToken, mathBot);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramChatBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
