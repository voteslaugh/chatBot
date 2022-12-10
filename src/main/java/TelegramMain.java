import bot.*;
import bot.CommandHandler;
import bot.TextHandler;
import bot.configs.BotConfig;
import bot.functions.*;
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
        File fileProperties = new File("..\\chatBot\\src\\main\\resources\\bot.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(fileProperties));
        String botName = properties.get("bot.name").toString();
        String botToken = properties.get("bot.token").toString();

        ChatHistoryRepository chatHistoryRepository = new ChatHistoryRepository();
        StatRepository statRepositoryForBinTest = new StatRepository();
        StatRepository statRepositoryForSimpleTest = new StatRepository();

        TaskGenerator taskGenerator = new TaskGenerator();

        Function help = new Info("""
                Вот список того, что я умею:
                /bintest - задачи на дополнительный код
                /simpletest - простые задачи на счёт
                """);
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.addCommand(new Command("/bintest", "Задачки на доп. код", new Test(taskGenerator, TestMode.BIN, statRepositoryForBinTest), true));
        commandHandler.addCommand(new Command("/simpletest", "Задачки на счет", new Test(taskGenerator, TestMode.SIMPLE, statRepositoryForSimpleTest), true));
        commandHandler.addCommand(new Command("/start", "Приветствие", new Info("""
                Привет, дорогой друг!👋
                Я бот, который поможет тебе тренироваться в математических задачах и не только.😎
                
                Вот список того, что я умею:
                /bintest - задачи на дополнительный код 0️⃣1️⃣1️⃣0️⃣
                /simpletest - задачи на счёт 1️⃣+1️⃣
                /binrate - рейтинг пользователей по задачам на доп.код 📊
                /simplerate - рейтинг пользователей по задачам на счёт 📊
                """), true));
        commandHandler.addCommand(new Command("Помощь", null, help , false,true));
        commandHandler.addCommand(new Command("/binrate", "Рейтинг бин тест", new Rating("Рейтинг решённых задач по дополнительному коду:", statRepositoryForBinTest), true));
        commandHandler.addCommand(new Command("/simplerate", "Рейтинг simple тест", new Rating("Рейтинг решённых примеров:",statRepositoryForSimpleTest), true));
        commandHandler.addCommand(new Command("/mystat", "Личная статистика", new PersonalStat("Ваша статистика\uD83E\uDDD0:\n\nРешено простых задач: %d\nРешено задач на доп. код: %d", statRepositoryForSimpleTest, statRepositoryForBinTest), true));

        TextHandler textHandler = new TextHandler(commandHandler);
        BotConfig botConfig = new BotConfig(chatHistoryRepository, textHandler);
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
