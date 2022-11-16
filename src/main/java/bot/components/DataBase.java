package bot.components;

import bot.models.ChatHistory;

import java.util.HashMap;

public class DataBase {
    HashMap<String, ChatHistory> history = new HashMap<>();

    public ChatHistory getChatHistory(String chatId) {
        return history.get(chatId);
    }

    public void save(String chatId, ChatHistory chatHistory) {
        history.put(chatId, chatHistory);
    }
}
