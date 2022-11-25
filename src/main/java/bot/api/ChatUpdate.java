package bot.api;

import bot.Message;

public class ChatUpdate {
    private String userId;
    private String chatId;
    private Message message = new Message();

    public ChatUpdate(String userId, String chatId) {
        this.userId = userId;
        this.chatId = chatId;
    }
    public void setCallback(String callback) {
        this.message.setCallback(callback);
    }
    public String getUserId() {
        return userId;
    }
    public String getChatId() {
        return chatId;
    }
    public void setText(String text) {
        this.message.setText(text);
    }

    public Message getMessage() {
        return message;
    }
}
