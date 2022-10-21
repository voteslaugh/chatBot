package bot;

import java.util.HashMap;

public class DataBase {
    HashMap<String, ChatHistory> users = new HashMap<>();

    public ChatHistory getChatHistory(String chatId) {
        if (!users.containsKey(chatId)) {
            users.put(chatId, new ChatHistory(BotStatus.SLEEPING));
        }
        return users.get(chatId);
    }
}
