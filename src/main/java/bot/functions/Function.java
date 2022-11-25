package bot.functions;

import bot.ChatHistory;
import bot.Message;

public interface Function {
    FunctionReply doFunction(ChatHistory chatHistory, Message message);
    FunctionReply startFunction(ChatHistory chatHistory);
}
