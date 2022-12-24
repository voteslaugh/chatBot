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
    Data data;
    bot.functions.Test test;

    @BeforeEach
    void prepareData() {
        chatHistory = new ChatHistory();
        data = new Data();
        functionReply = new FunctionReply();
        chatUpdate = new ChatUpdate("", new User());
        test = new  bot.functions.Test(new TaskGenerator(), TestMode.SIMPLE, new StatRepository(),
                new BotResponseVariants(new String[]{"Right answer"}, new String[]{"Wrong answer"}, new String[]{"Bad form"}));
    }

    @Test
    void testSimpleDoFunctionEasyRightAnswer() {
        chatUpdate.setText("1");
        chatHistory.setTask(new Task("2-1", "1", Difficulty.EASY));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Right answer") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && data.getInLineKeyboard() != null && !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionEasyWrongAnswer() {
        chatUpdate.setText("0");
        chatHistory.setTask(new Task("2-1", "1", Difficulty.EASY));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Wrong answer") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionEasyBadForm() {
        chatUpdate.setText("asd");
        chatHistory.setTask(new Task("2-1", "1", Difficulty.EASY));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Bad form") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionMediumRightAnswer() {
        chatUpdate.setText("1");
        chatHistory.setTask(new Task("2-1", "1", Difficulty.MEDIUM));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Right answer") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionMediumWrongAnswer() {
        chatUpdate.setText("0");
        chatHistory.setTask(new Task("2-1", "1", Difficulty.MEDIUM));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Wrong answer") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionMediumBadForm() {
        chatUpdate.setText("asd");
        chatHistory.setTask(new Task("2-1", "1", Difficulty.MEDIUM));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Bad form") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionHardRightAnswer() {
        chatUpdate.setText("1");
        chatHistory.setTask(new Task("2-1", "1", Difficulty.HARD));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Right answer") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionHardWrongAnswer() {
        chatUpdate.setText("0");
        chatHistory.setTask(new Task("2-1", "1", Difficulty.HARD));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Wrong answer") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionHardBadForm() {
        chatUpdate.setText("asd");
        chatHistory.setTask(new Task("2-1", "1", Difficulty.HARD));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Bad form") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionExtremeRightAnswer() {
        chatUpdate.setText("1");
        chatHistory.setTask(new Task("2-1", "1", Difficulty.EXTREME));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Right answer") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionExtremeWrongAnswer() {
        chatUpdate.setText("0");
        chatHistory.setTask(new Task("2-1", "1", Difficulty.EXTREME));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Wrong answer") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testSimpleDoFunctionExtremeBadForm() {
        chatUpdate.setText("asd");
        chatHistory.setTask(new Task("2-1", "1", Difficulty.EXTREME));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Bad form") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testAdditionalDoFunctionRightAnswer() {
        test.setTestMode(TestMode.BIN);
        chatUpdate.setText("1");
        chatHistory.setTask(new Task("0001", "1", Difficulty.EASY));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Right answer") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testAdditionalDoFunctionWrongAnswer() {
        chatUpdate.setText("0");
        chatHistory.setTask(new Task("0001", "1", Difficulty.EXTREME));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Wrong answer") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testAdditionalDoFunctionBadForm() {
        chatUpdate.setText("asd");
        chatHistory.setTask(new Task("0001", "1", Difficulty.EXTREME));
        functionReply = test.doFunction(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().contains("Bad form") && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testSimplePreprocess() {
        functionReply = test.preprocess(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().equals("Решите пример:\n\n" + newQuestion) && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }

    @Test
    void testAdditionalPreprocess() {
        test.setTestMode(TestMode.BIN);
        functionReply = test.preprocess(chatHistory, chatUpdate);
        data = functionReply.getData();
        String newQuestion = chatHistory.getTask().getQuestion();
        String newAnswer = chatHistory.getTask().getAnswer();
        Assertions.assertTrue(data.getText().equals("Дано число, представленное в виде дополнительного двоичного кода.\n" +
                "Переведите его в 10-ую систему счисления:\n\n" + newQuestion) && !newQuestion.isEmpty() &&
                !newAnswer.isEmpty() && !functionReply.isFinished());
    }
}