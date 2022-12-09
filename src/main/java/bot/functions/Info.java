package bot.functions;

import bot.ChatHistory;
import bot.api.ChatUpdate;

public class Info implements Function {
    private String text;

    public Info(String text) {
        this.text = text;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, ChatUpdate chatUpdate) {
        FunctionReply functionReply = new FunctionReply();
        Data data = new Data();
        data.setText(text);
        functionReply.finish();
        functionReply.setData(data);
        return functionReply;
    }

    @Override
    public FunctionReply preprocess(ChatHistory chatHistory, ChatUpdate chatUpdate) {
        return doFunction(chatHistory, null);
    }
}
