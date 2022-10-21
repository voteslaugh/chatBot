import bot.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramMain {
    public static void main(String[] args) {
        String botName = "omjlet_test_bot";
        String botToken = "5538094972:AAHf2r6xYJR6Y4-zpVkWpQwUH2o44wlnoUc";
        DataBase dataBase = new DataBase();
        Bot mathBot = new MathBot(dataBase);
        TelegramChatBot telegramChatBot = new TelegramChatBot(botName, botToken, mathBot);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramChatBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
