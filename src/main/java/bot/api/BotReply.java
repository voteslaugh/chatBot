package bot.api;

import bot.functions.api.FunctionReply;
import bot.functions.models.Button;
import bot.functions.models.InLineButton;

import java.util.List;

public class BotReply {
    private String userId;
    private String chatId;
    FunctionReply functionReply;

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
        return functionReply.getText();
    }

    public void setFunctionReply(FunctionReply functionReply) {
        this.functionReply = functionReply;
    }

    public List<List<InLineButton>> getInLineKeyboard() {return this.functionReply.getInLineKeyboard();}
    public List<List<Button>> getKeyboard(){return this.functionReply.getKeyboard();}
}
