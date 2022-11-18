package bot.functions.services;

import bot.functions.api.FunctionReply;
import bot.models.ChatHistory;

public class Message implements Function {
    String text;

    public Message(String text) {
        this.text = text;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String messageText) {
        FunctionReply functionReply = new FunctionReply();
        functionReply.setText(text);
        return functionReply;
    }
}
