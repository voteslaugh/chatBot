package bot.functions;

import bot.ChatHistory;
import bot.Message;

public interface Function { // продумать названия
    FunctionReply doFunction(ChatHistory chatHistory, Message message);
    FunctionReply preprocess(ChatHistory chatHistory);
}
