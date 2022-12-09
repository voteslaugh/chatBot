package bot.functions;

import bot.ChatHistory;
import bot.api.ChatUpdate;

public interface Function { // продумать названия
    FunctionReply doFunction(ChatHistory chatHistory, ChatUpdate chatUpdate);
    FunctionReply preprocess(ChatHistory chatHistory, ChatUpdate chatUpdate);
}
