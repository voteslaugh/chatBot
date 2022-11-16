package bot.api;

public class BotReply {
    private String userId;
    private String chatId;
    private String text = "";

    public BotReply(String userId, String chatId) {
        this.userId = userId;
        this.chatId = chatId;
    }

    public String getUserId() {
        return userId;
    }

    public String getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
