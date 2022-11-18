package bot.functions.services;

import bot.functions.api.FunctionReply;
import bot.functions.components.TaskGenerator;
import bot.models.ChatHistory;

public class EasyTest implements Function {
    private TaskGenerator taskGenerator;

    public EasyTest(TaskGenerator taskGenerator) {
        this.taskGenerator = taskGenerator;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String messageText) {
        return null;
    }

}
