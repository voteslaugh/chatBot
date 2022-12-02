package bot.functions;

import bot.ChatHistory;
import bot.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinTest implements Function {
    private TaskGenerator taskGenerator;

    public BinTest(TaskGenerator taskGenerator) {
        this.taskGenerator = taskGenerator;
    }

    private String processCallback(ChatHistory chatHistory, String callback) {
        return switch (callback) {
            case "binhelp" -> """
                    Чтобы перевести число в дополнительный код, нужно:
                    Если число >= 0, перевести в двоичную СС
                    Если число < 0:
                    1) Записываем модуль числа в двоичную СС
                    2) Инвертируем все разряды
                    3) Прибавляем единицу
                    """;
            case "giveup" -> {
                String answer = chatHistory.getTask().getAnswer();
                Task task = taskGenerator.getAdditionalCode();
                chatHistory.setTask(task);
                yield "Ответ был: " + answer + "\n\n" + "Новый пример: " + task.getQuestion();
            }
            default -> null;
        };
    }
    private List<List<InLineButton>> addKeyboard() {
        InLineButton helpButton = new InLineButton("Binary help", "binhelp");
        InLineButton giveUpButton = new InLineButton("Give up", "giveup");
        List<List<InLineButton>> linesOfButtons = new ArrayList<>();
        List<InLineButton> inLineButtons = new ArrayList<>();
        inLineButtons.add(helpButton);
        inLineButtons.add(giveUpButton);
        linesOfButtons.add(inLineButtons);
        return linesOfButtons;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, Message message) {
        Task task = chatHistory.getTask();
        FunctionReply functionReply = new FunctionReply();

        if (message.hasCallback()) {
            functionReply.setText(processCallback(chatHistory, message.getCallback()));
            return functionReply;
        } else if (Objects.equals(message, task.getAnswer())) {
            task = taskGenerator.getAdditionalCode();
            functionReply.setText("Excellently!\n" + task.getQuestion());
        } else {
            functionReply.setText("Wrong answer. Try again!\n" + task.getQuestion());
        }
        chatHistory.setTask(task);
        functionReply.setInLineKeyboard(addKeyboard());
        return functionReply;
    }

    @Override
    public FunctionReply startFunction(ChatHistory chatHistory) {
        FunctionReply functionReply = new FunctionReply();
        Task task = taskGenerator.getAdditionalCode();
        chatHistory.setTask(task);
        functionReply.setText(task.getQuestion());
        functionReply.setInLineKeyboard(addKeyboard());
        return functionReply;
    }
}
