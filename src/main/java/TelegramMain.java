import bot.*;
import bot.CommandHandler;
import bot.TextHandler;
import bot.configs.BotConfig;
import bot.functions.*;
import bot.DataBase;
import bot.KeyboardFactory;
import bot.ChatBot;
import bot.TelegramChatBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TelegramMain {
    public static void main(String[] args) throws IOException {
        File fileProperties = new File("..\\chatBot\\src\\main\\resources\\bot_example.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(fileProperties));
        String botName = properties.get("bot.name").toString();
        String botToken = properties.get("bot.token").toString();

        DataBase dataBase = new DataBase();
        TaskGenerator taskGenerator = new TaskGenerator();

        CommandHandler commandHandler = new CommandHandler();
        commandHandler.addCommand(new Command("/bintest", null, new BinTest(taskGenerator), true));
        commandHandler.addCommand(new Command("/eztest", null, new EasyTest(taskGenerator)));
        commandHandler.addCommand(new Command("/start", null, new Info("""
                        Привет, дорогой друг!
                Я бот, который поможет тебе тренироваться
                   в математических задачах и не только.
                Для более подробной информации введи "/help"
                """), true));
        commandHandler.addCommand(new Command("/help", null, new Info("""
                Вот список того, что я умею:
                1) "/bintest", чтобы порешать
                    задачи на дополнительный код
                2) "/eztest", чтобы решать
                    простые задачи на счёт
                """), true));

        TextHandler textHandler = new TextHandler(commandHandler);
        BotConfig botConfig = new BotConfig(dataBase, textHandler);
        Bot mathBot = new ChatBot(botConfig);

        TelegramChatBot telegramChatBot = new TelegramChatBot(botName, botToken, mathBot, new KeyboardFactory());
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramChatBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
