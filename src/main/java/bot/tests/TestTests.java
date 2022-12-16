package bot.tests;

import bot.ChatHistory;
import bot.StatRepository;
import bot.User;
import bot.api.ChatUpdate;
import bot.functions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestTests {
    ChatHistory chatHistory;
    ChatUpdate chatUpdate;
    FunctionReply functionReply;
    Task task;
    Data data;
    int answer;
    bot.functions.Test test;

    @BeforeEach
    void prepareData() {
        chatHistory = new ChatHistory();
        chatHistory.setTask(new Task("1+1", "2", Difficulty.EASY));
        chatUpdate = new ChatUpdate("1", new User());
        chatUpdate.setText("2");
        data = new Data();
        functionReply = new FunctionReply();
        task = new Task("1+1", "2", Difficulty.EASY);
        test = new  bot.functions.Test(new TaskGenerator(), TestMode.SIMPLE, new StatRepository(),
                new BotResponseVariants(new String[]{""}, new String[]{""}, new String[]{""}));
    }

    private void updateDoFunction(FunctionReply functionReply) {
        functionReply = test.doFunction(chatHistory, chatUpdate);
        task = chatHistory.getTask();
        data = functionReply.getData();
        answer = Integer.parseInt(task.getAnswer());
    }

    private void updatePreprocess(FunctionReply functionReply) {
        functionReply = test.preprocess(chatHistory, chatUpdate);
        task = chatHistory.getTask();
        data = functionReply.getData();
        answer = Integer.parseInt(task.getAnswer());
    }

    @Test
    void testSimpleDoFunctionEasy() {
        updateDoFunction(functionReply);
        Assertions.assertTrue(task.getDifficulty() == Difficulty.EASY &&
                !task.getQuestion().isEmpty() && Math.abs(answer) <= 100 &&
                data.getInLineKeyboard() != null && data.getText() != null &&
                !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionMedium() {
        chatHistory.setTask(new Task("", "2", Difficulty.MEDIUM));
        updateDoFunction(functionReply);
        Assertions.assertTrue(task.getDifficulty() == Difficulty.MEDIUM &&
                !task.getQuestion().isEmpty() && Math.abs(answer) <= 10000 &&
                data.getInLineKeyboard() != null && data.getText() != null &&
                !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionHard() {
        chatHistory.setTask(new Task("", "2", Difficulty.HARD));
        updateDoFunction(functionReply);
        Assertions.assertTrue(task.getDifficulty() == Difficulty.HARD &&
                !task.getQuestion().isEmpty() && Math.abs(answer) <= 1000000 &&
                data.getInLineKeyboard() != null && data.getText() != null &&
                !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionExtreme() {
        chatHistory.setTask(new Task("", "2", Difficulty.EXTREME));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        task = chatHistory.getTask();
        data = functionReply.getData();
        long answer = Long.parseLong(task.getAnswer());
        Assertions.assertTrue(task.getDifficulty() == Difficulty.EXTREME &&
                !task.getQuestion().isEmpty() && Math.abs(answer) <= 1000000000000L &&
                data.getInLineKeyboard() != null && data.getText() != null &&
                !functionReply.isFinished());
    }

    @Test
    void testAdditionalDoFunction() {
        test.setTestMode(TestMode.BIN);
        updateDoFunction(functionReply);
        Assertions.assertTrue(task.getDifficulty() == Difficulty.EASY &&
                !task.getQuestion().isEmpty() && Math.abs(answer) <= 7 &&
                data.getInLineKeyboard() != null && data.getText() != null &&
                !functionReply.isFinished());
    }

    @Test
    void testSimplePreprocess() {
        updatePreprocess(functionReply);
        Assertions.assertTrue(task.getDifficulty() == Difficulty.EASY &&
                !task.getQuestion().isEmpty() && Math.abs(answer) <= 100 &&
                data.getInLineKeyboard() != null && data.getText() != null &&
                !functionReply.isFinished());
    }

    @Test
    void testAdditionalPreprocess() {
        test.setTestMode(TestMode.BIN);
        updatePreprocess(functionReply);
        Assertions.assertTrue(task.getDifficulty() == Difficulty.EASY &&
                !task.getQuestion().isEmpty() && Math.abs(answer) <= 7 &&
                data.getInLineKeyboard() != null && data.getText() != null &&
                !functionReply.isFinished());
    }
}