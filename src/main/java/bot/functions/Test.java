package bot.functions;

import bot.ChatHistory;
import bot.Message;
import bot.Stat;
import bot.StatRepository;
import bot.api.ChatUpdate;

import java.util.*;

public class Test implements Function {
    private TaskGenerator taskGenerator;
    private TestMode mode;
    private StatRepository statRepository;
    private BotResponseVariants botResponseVariants;
    public Test(TaskGenerator taskGenerator, TestMode mode, StatRepository statRepository) {
        this.taskGenerator = taskGenerator;
        this.mode = mode;
        this.statRepository = statRepository;
    }
    private Task getTask(Difficulty difficulty) {
        if (mode == TestMode.BIN)
            return taskGenerator.getAdditionalCode();
        else
            return taskGenerator.getSimpleTask(difficulty);
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
                Task lastTask = chatHistory.getTask();
                String answer = lastTask.getAnswer();
                Task task = getTask(lastTask.getDifficulty());
                chatHistory.setTask(task);
                data.setInLineKeyboard(addKeyboard(task.getDifficulty()));
                yield "Ответ был: " + answer + "\n\n" + "Новый пример: " + task.getQuestion();
            }
            case "increase" -> {
                if (mode != TestMode.SIMPLE)
                    yield "Невозможно увеличить сложность для данного режима\uD83E\uDEE3\uD83E\uDD2D";
                Map<Difficulty, String> botAnswers = new HashMap<Difficulty, String>() {{
                    put(Difficulty.MEDIUM,"\uD83E\uDDD0Повышена с лёгкой до средней\uD83D\uDC4D\n\n");
                    put(Difficulty.HARD, "\uD83D\uDE0E\uD83D\uDE0EПовышена со средней до сложной\uD83E\uDD29\n\n");
                    put(Difficulty.EXTREME, "\uD83D\uDE35\u200D\uD83D\uDCABПовышена со сложной до экстремальной\uD83E\uDEE1\n\n");
                }};
                Task lastTask = chatHistory.getTask();
                Difficulty difficultyNow = taskGenerator.increaseDifficulty(lastTask.getDifficulty());
                Task task = getTask(difficultyNow);
                chatHistory.setTask(task);
                data.setInLineKeyboard(addKeyboard(task.getDifficulty()));
                yield botAnswers.get(difficultyNow) + task.getQuestion();
            }
            case "reduce" -> {
                if (mode != TestMode.SIMPLE)
                    yield "Невозможно уменьшить сложность для данного режима\uD83E\uDEE3\uD83E\uDD2D";
                Map<Difficulty, String> botAnswers = new HashMap<Difficulty, String>() {{
                    put(Difficulty.EASY,"\uD83E\uDD71Понижена со средней до лёгкой\uD83E\uDD71\n\n");
                    put(Difficulty.MEDIUM, "\uD83D\uDE2E\u200D\uD83D\uDCA8Понижена со сложной до средней\uD83E\uDD14\n\n");
                    put(Difficulty.HARD, "\uD83D\uDE2E\u200D\uD83D\uDCA8\uD83D\uDE2E\u200D\uD83D\uDCA8Понижена с экстремальной до сложной\uD83D\uDE2E\u200D\uD83D\uDCA8\uD83D\uDE2E\u200D\uD83D\uDCA8\n\n");
                }};
                Task lastTask = chatHistory.getTask();
                Difficulty difficultyNow = taskGenerator.reduceDifficulty(lastTask.getDifficulty());
                Task task = getTask(difficultyNow);
                chatHistory.setTask(task);
                data.setInLineKeyboard(addKeyboard(difficultyNow));
                yield botAnswers.get(difficultyNow) + task.getQuestion();
            }
            default -> null;
        });

        return data;
    }
    private List<List<InLineButton>> addKeyboard(Difficulty difficulty) {
        InLineButton helpButton = new InLineButton("Подсказка", "binhelp");
        InLineButton giveUpButton = new InLineButton("Пропустить", "giveup");
        InLineButton increaseDifficulty = new InLineButton("⬆ сложность", "increase");
        InLineButton reduceDifficulty = new InLineButton("⬇ сложность", "reduce");
        List<List<InLineButton>> linesOfButtons = new ArrayList<>();
        List<InLineButton> inLineButtons = new ArrayList<>();
        if (mode == TestMode.BIN)
            inLineButtons.add(helpButton);

        inLineButtons.add(giveUpButton);

        if (difficulty == Difficulty.EASY && mode == TestMode.SIMPLE)
            inLineButtons.add(increaseDifficulty);
        else if (difficulty == Difficulty.EXTREME && mode == TestMode.SIMPLE)
            inLineButtons.add(reduceDifficulty);
        else if (mode == TestMode.SIMPLE){
            inLineButtons.add(reduceDifficulty);
            inLineButtons.add(increaseDifficulty);
        }

        linesOfButtons.add(inLineButtons);
        return linesOfButtons;
    }
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, ChatUpdate chatUpdate) {
        Message message = chatUpdate.getMessage();
        Task task = chatHistory.getTask();
        FunctionReply functionReply = new FunctionReply();
        Data data = new Data();

        if (message.hasCallback()) {
            functionReply.setData(processCallback(chatHistory, message.getCallback()));
            return functionReply;
        }
        if (isNumeric(message.getText())) {
            if (Objects.equals(message.getText(), task.getAnswer())) {
                Task lastTask = chatHistory.getTask();
                task = getTask(lastTask.getDifficulty());
                Stat stat = statRepository.getStat(chatUpdate.getChatId());

                if (stat == null) {
                    stat = new Stat(chatUpdate.getUser());
                    statRepository.save(chatUpdate.getChatId(), stat);
                }

                stat.update();
                data.setText(botResponseVariants.getRandomReply(BotResponseCase.RIGHT_ANSWER) + task.getQuestion());
            } else {
                data.setText(botResponseVariants.getRandomReply(BotResponseCase.WRONG_ANSWER) + task.getQuestion());
            }
            data.setInLineKeyboard(addKeyboard(task.getDifficulty()));
        } else {
            data.setText(botResponseVariants.getRandomReply(BotResponseCase.BAD_FORM) + task.getQuestion());
        }
        chatHistory.setTask(task);
        functionReply.setData(data);
        return functionReply;
    }

    @Override
    public FunctionReply preprocess(ChatHistory chatHistory, ChatUpdate chatUpdate) {
        FunctionReply functionReply = new FunctionReply();
        Data data = new Data();
        Task task = getTask(Difficulty.EASY);
        chatHistory.setTask(task);
        switch (mode) {
            case BIN -> data.setText("Дано число, представленное в виде дополнительного двоичного кода.\n" +
                    "Переведите его в 10-ую систему счисления:\n\n" + task.getQuestion());
            case SIMPLE -> data.setText("Решите пример:\n\n" + task.getQuestion());
        }
        data.setInLineKeyboard(addKeyboard(task.getDifficulty()));
        functionReply.setData(data);
        return functionReply;
    }
}
