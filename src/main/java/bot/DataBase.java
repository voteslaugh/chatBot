package bot;

import java.util.HashMap;

public class DataBase {
    HashMap<String, ChatHistory> users = new HashMap<>();

    public ChatHistory getChatHistory(String userId) {
        if (users.containsKey(userId)) {
            return users.get(userId);
        } else {
            users.put(userId, new ChatHistory(BotStatus.SLEEPING));
            return users.get(userId);
        }
    }
}
