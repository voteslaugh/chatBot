package bot;

public class BotReply {
    private String userId;
    private String chatId;
    private BotStatus status;
    private Message message = null;

    public BotReply(String userId, String chatId, BotStatus status) {
        this.userId = userId;
        this.chatId = chatId;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public String getChatId() {
        return chatId;
    }

    public BotStatus getStatus() {
        return status;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
