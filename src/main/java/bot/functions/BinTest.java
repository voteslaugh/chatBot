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

    private Data processCallback(ChatHistory chatHistory, String callback) {
        Data data = new Data();
        data.setText(switch (callback) {
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
                data.setInLineKeyboard(addKeyboard());
                yield "Ответ был: " + answer + "\n\n" + "Новый пример: " + task.getQuestion();
            }
            default -> null;
        });

        return data;
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
        Data data = new Data();

        if (message.hasCallback()) {
            functionReply.setData(processCallback(chatHistory, message.getCallback()));
            return functionReply;
        } else if (Objects.equals(message.getText(), task.getAnswer())) {
            task = taskGenerator.getAdditionalCode();
            data.setText("Excellently!\n" + task.getQuestion());
        } else {
            data.setText("Wrong answer. Try again!\n" + task.getQuestion());
        }
        chatHistory.setTask(task);
        data.setInLineKeyboard(addKeyboard());
        functionReply.setData(data);
        return functionReply;
    }

    @Override
    public FunctionReply preprocess(ChatHistory chatHistory) {
        FunctionReply functionReply = new FunctionReply();
        Data data = new Data();
        Task task = taskGenerator.getAdditionalCode();
        chatHistory.setTask(task);
        data.setText(task.getQuestion());
        data.setInLineKeyboard(addKeyboard());
        functionReply.setData(data);
        return functionReply;
    }
}
