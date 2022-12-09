package bot.api;

import bot.Message;
import bot.User;

public class ChatUpdate {
    private User user;
    private String chatId;
    private Message message = new Message();

    public ChatUpdate(String chatId, User user) {
        this.chatId = chatId;
        this.user = user;
    }
    public void setCallback(String callback) {
        this.message.setCallback(callback);
    }
    public String getChatId() {
        return chatId;
    }
    public Message getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
    public void setText(String text) {
        message.setText(text);
    }
}
