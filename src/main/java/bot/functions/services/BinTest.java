package bot.functions.services;

import bot.functions.api.FunctionReply;
import bot.functions.models.Task;
import bot.functions.components.TaskGenerator;
import bot.models.ChatHistory;

import java.util.Objects;

public class BinTest implements Function {
    private TaskGenerator taskGenerator;

    public BinTest(TaskGenerator taskGenerator) {
        this.taskGenerator = taskGenerator;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, String messageText) {
        Task task = chatHistory.getTask();
        FunctionReply functionReply = new FunctionReply();
        if (messageText == null) {
            task = taskGenerator.getAdditionalCode();
            chatHistory.setTask(task);
            functionReply.setText(task.getQuestion());
            return functionReply;
        }
        if (Objects.equals(messageText, task.getAnswer())) {
            task = taskGenerator.getAdditionalCode();
            functionReply.setText("Excellently!\n" + task.getQuestion());
        } else {
            functionReply.setText("Wrong answer. Try again!\n" + task.getQuestion());
        }
        chatHistory.setTask(task);
        return functionReply;
    }
}
