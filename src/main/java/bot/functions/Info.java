package bot.functions;

import bot.ChatHistory;
import bot.Message;

public class Info implements Function {
    private String text;

    public Info(String text) {
        this.text = text;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, Message message) {
        FunctionReply functionReply = new FunctionReply();
        functionReply.setText(text);
        chatHistory.setNameCommand(null);
        return functionReply;
    }

    @Override
    public FunctionReply startFunction(ChatHistory chatHistory) {
        return doFunction(chatHistory, null);
    }
}
