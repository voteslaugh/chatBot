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
        Data data = new Data();
        data.setText(text);
        functionReply.setFinishedWork(true);
        functionReply.setData(data);
        return functionReply;
    }

    @Override
    public FunctionReply preprocess(ChatHistory chatHistory) {
        return doFunction(chatHistory, null);
    }
}
