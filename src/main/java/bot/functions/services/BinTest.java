package bot.functions.services;

import bot.functions.api.FunctionReply;
import bot.functions.components.TaskGenerator;
import bot.functions.models.InLineButton;
import bot.functions.models.Task;
import bot.models.ChatHistory;

import java.util.ArrayList;
import java.util.List;
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
        }
        else if (messageText.equals("binhelp")){
            functionReply.setText("здесь должна быть теоретическая помощь по доп.коду");
            return functionReply;
        }
        else if(messageText.equals("giveup")){
            String answer = task.getAnswer();
            task = taskGenerator.getAdditionalCode();
            chatHistory.setTask(task);
            functionReply.setText("Ответ был: "+answer+"\n\n"+"Новый пример: "+task.getQuestion());
        }
        else if (Objects.equals(messageText, task.getAnswer())) {
            task = taskGenerator.getAdditionalCode();
            functionReply.setText("Excellently!\n" + task.getQuestion());
        } else {
            functionReply.setText("Wrong answer. Try again!\n" + task.getQuestion());
        }
        chatHistory.setTask(task);
        InLineButton helpButton = new InLineButton("Binary help", "binhelp");
        InLineButton giveUpButton = new InLineButton("Give up", "giveup");
        List<List<InLineButton>> linesOfButtons = new ArrayList<>();
        List<InLineButton> inLineButtons = new ArrayList<>();
        inLineButtons.add(helpButton);
        inLineButtons.add(giveUpButton);
        linesOfButtons.add(inLineButtons);
        functionReply.setInLineKeyboard(linesOfButtons);
        return functionReply;
    }
}
