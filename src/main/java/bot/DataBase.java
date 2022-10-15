package bot;

import java.util.HashMap;

public class DataBase {
    HashMap<String, UserChatHistory> users = new HashMap<>();

    public UserChatHistory getChatHistory(String userId) {
        if (!users.containsKey(userId)) {
            users.put(userId, new UserChatHistory(BotStatus.SLEEPING));
        }
        return users.get(userId);
    }
}
