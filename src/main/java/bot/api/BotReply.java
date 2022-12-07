package bot.api;

import bot.functions.Data;
import bot.functions.Button;
import bot.functions.InLineButton;

import java.util.List;

public class BotReply {
    private String userId;
    private String chatId;
    Data data;

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
        return data.getText();
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<List<InLineButton>> getInLineKeyboard() {return this.data.getInLineKeyboard();}
    public List<List<Button>> getKeyboard(){return this.data.getKeyboard();}
}
