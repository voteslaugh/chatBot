package bot.functions.services;

import bot.functions.api.FunctionReply;
import bot.models.ChatHistory;

public abstract class Function {
    public abstract FunctionReply doFunction(ChatHistory chatHistory, String messageText);
}
