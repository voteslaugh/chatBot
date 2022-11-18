import bot.*;
import bot.components.CommandHandler;
import bot.components.TextHandler;
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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TelegramMain {
    public static void main(String[] args) throws IOException {
        File fileProperties = new File("D:\\CODE\\Java\\chatBot\\src\\main\\resources\\bot.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(fileProperties));
        String botName = properties.get("bot.name").toString();
        String botToken = properties.get("bot.token").toString();

        DataBase dataBase = new DataBase();
        TaskGenerator taskGenerator = new TaskGenerator();

        Function sleep = new Message(" (-, – )…zzzZZZ");
        Function help = new Message("Здесь должен быть хелп");
        Function waitCommand = new Message("Жду команду...");
        Function bintest = new BinTest(taskGenerator);

        CommandHandler commandHandler = new CommandHandler();
        commandHandler.addCommand(new Command(sleep, true, new FunctionGroup(waitCommand, bintest), "/sleep"));
        commandHandler.addCommand(new Command(waitCommand, true, new FunctionGroup(sleep), "/start"));
        commandHandler.addCommand(new Command(waitCommand, true, new FunctionGroup(bintest), "/stop"));
        commandHandler.addCommand(new Command(bintest, true, new FunctionGroup(waitCommand), "/bintest"));
        commandHandler.addCommand(new Command(help, false, new FunctionGroup(waitCommand, bintest), "/help", "/h"));

        TextHandler textHandler = new TextHandler(commandHandler);
        BotConfig botConfig = new BotConfig(dataBase, textHandler);
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
