package bot.functions;

import bot.ChatHistory;
import bot.Message;

public class EasyTest implements Function {
    private TaskGenerator taskGenerator;

    public EasyTest(TaskGenerator taskGenerator) {
        this.taskGenerator = taskGenerator;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, Message message) {
        return null;
    }

    @Override
    public FunctionReply startFunction(ChatHistory chatHistory) {
        return null;
    }
}
