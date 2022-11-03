import bot.*;
import components.DataBase;
import components.MathBot;
import components.TaskGenerator;
import services.TelegramChatBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramMain {
    public static void main(String[] args) {
        String botName = "omjlet_test_bot";
        String botToken = "5538094972:AAF6LbxoUTJTQzTEjjga6NH4tETfatjm1z4";
        DataBase dataBase = new DataBase();
        TaskGenerator taskGenerator = new TaskGenerator();
        Bot mathBot = new MathBot(taskGenerator, dataBase);
        TelegramChatBot telegramChatBot = new TelegramChatBot(botName, botToken, mathBot);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramChatBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
