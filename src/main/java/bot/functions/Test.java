package bot.functions;

import bot.ChatHistory;
import bot.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Test implements Function {
    private TaskGenerator taskGenerator;
    private TestMode mode;

    public Test(TaskGenerator taskGenerator, TestMode mode) {
        this.taskGenerator = taskGenerator;
        this.mode = mode;
    }
    private Task getTask() {
        if (mode == TestMode.BIN)
            return taskGenerator.getAdditionalCode();
        else
            return taskGenerator.getSimpleTask();
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
                Task task = getTask();
                chatHistory.setTask(task);
                data.setInLineKeyboard(addKeyboard());
                yield "Ответ был: " + answer + "\n\n" + "Новый пример: " + task.getQuestion();
            }
            default -> null;
        });

        return data;
    }
    private List<List<InLineButton>> addKeyboard() {
        InLineButton helpButton = new InLineButton("Подсказка", "binhelp");
        InLineButton giveUpButton = new InLineButton("Пропустить", "giveup");
        List<List<InLineButton>> linesOfButtons = new ArrayList<>();
        List<InLineButton> inLineButtons = new ArrayList<>();
        if (mode == TestMode.BIN)
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
            task = getTask();
            String[] botAnswer  = new String[] {"\uD83E\uDD79Я тобой горжусь! Следующий пример:\n\n", "\uD83E\uDD73Отлично! Следующий пример:\n\n"};
            int index = (int) (Math.random()*((1)+1));
            data.setText(botAnswer[index] + task.getQuestion());
        } else {
            String[] botAnswer  = new String[] {"\uD83D\uDE1BНеверно. Попробуй снова!\n\n", "\uD83E\uDD2AНеверно. Попробуй ещё раз!\n\n", "\uD83D\uDE04Не-а. Заново!\n\n"};
            int index = (int) (Math.random()*((2)+1));
            data.setText(botAnswer[index] + task.getQuestion());
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
        Task task = getTask();
        chatHistory.setTask(task);
        switch (mode) {
            case BIN -> data.setText("Дано число, представленное в виде дополнительного двоичного кода.\n" +
                    "Переведите его в 10-ую систему счисления:\n\n" + task.getQuestion());
            case SIMPLE -> data.setText("Решите пример:\n\n" + task.getQuestion());
        }
        data.setInLineKeyboard(addKeyboard());
        functionReply.setData(data);
        return functionReply;
    }
}
