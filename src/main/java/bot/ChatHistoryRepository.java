package bot;

import java.util.HashMap;

public class ChatHistoryRepository implements Repository {
    private HashMap<String, ChatHistory> history = new HashMap<>();
    public ChatHistory getChatHistory(String chatId) {
        return history.get(chatId);
    }
    public void save(String chatId, ChatHistory chatHistory) {
        history.put(chatId, chatHistory);
    }
}
