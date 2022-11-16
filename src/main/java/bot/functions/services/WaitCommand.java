package bot.functions.services;

import bot.functions.api.FunctionReply;
import bot.models.ChatHistory;

public class WaitCommand extends Function {

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String messageText) {
        FunctionReply functionReply = new FunctionReply();
        functionReply.setText("I'm waiting command...");
        return functionReply;
    }
}
