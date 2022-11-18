package bot.functions.services;

import bot.functions.api.FunctionReply;
import bot.models.ChatHistory;

public interface Function {
    FunctionReply doFunction(ChatHistory chatHistory, String messageText);
}
