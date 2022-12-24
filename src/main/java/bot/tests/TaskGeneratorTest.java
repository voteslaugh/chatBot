package bot.tests;

import bot.functions.Difficulty;
import bot.functions.Task;
import bot.functions.TaskGenerator;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class TaskGeneratorTest {
    TaskGenerator taskGenerator;
    Task task;

    @BeforeEach
    void prepareData() {
        taskGenerator = new TaskGenerator();
    }

    @Test
    void testGetOperation() {
        Assertions.assertNull(taskGenerator.getOperation());
    }

    @Test
    void testSetRandom() {
        taskGenerator.setRandom();
        Assertions.assertNotNull(taskGenerator.getOperation());
    }

    @Test
    void testIncreaseDifficulty() {
        task = new Task(null, null, Difficulty.EASY);
        Difficulty increasedDifficulty = taskGenerator.increaseDifficulty(task.getDifficulty());
        Assertions.assertEquals(increasedDifficulty, Difficulty.MEDIUM);
    }

    @Test
    void testReduceDifficulty() {
        task = new Task(null, null, Difficulty.EXTREME);
        Difficulty reducedDifficulty = taskGenerator.reduceDifficulty(task.getDifficulty());
        Assertions.assertEquals(reducedDifficulty, Difficulty.HARD);
    }

    @RepeatedTest(10)
    void testGetEasySimpleTask() {
        Difficulty difficulty = Difficulty.EASY;
        Task simpleTask = taskGenerator.getSimpleTask(difficulty);
        Expression expression = new ExpressionBuilder(simpleTask.getQuestion()).build();
        int answer = (int) expression.evaluate();
        int botAnswer = Integer.parseInt(simpleTask.getAnswer());
        Assertions.assertTrue(simpleTask.getDifficulty() == Difficulty.EASY &&
                answer == botAnswer && simpleTask.getQuestion() != null);
    }

    @RepeatedTest(10)
    void testGetMediumSimpleTask() {
        Difficulty difficulty = Difficulty.MEDIUM;
        Task simpleTask = taskGenerator.getSimpleTask(difficulty);
        Expression expression = new ExpressionBuilder(simpleTask.getQuestion()).build();
        int answer = (int) expression.evaluate();
        int botAnswer = Integer.parseInt(simpleTask.getAnswer());
        Assertions.assertTrue(simpleTask.getDifficulty() == Difficulty.MEDIUM &&
                answer == botAnswer && simpleTask.getQuestion() != null);
    }

    @RepeatedTest(5)
    void testGetHardSimpleTask() {
        Difficulty difficulty = Difficulty.HARD;
        Task simpleTask = taskGenerator.getSimpleTask(difficulty);
        Expression expression = new ExpressionBuilder(simpleTask.getQuestion()).build();
        int answer = (int) expression.evaluate();
        int botAnswer = Integer.parseInt(simpleTask.getAnswer());
        Assertions.assertTrue(simpleTask.getDifficulty() == Difficulty.HARD &&
                answer == botAnswer && simpleTask.getQuestion() != null);
    }

    @RepeatedTest(5)
    void testGetExtremeSimpleTask() {
        Difficulty difficulty = Difficulty.EXTREME;
        Task simpleTask = taskGenerator.getSimpleTask(difficulty);
        Expression expression = new ExpressionBuilder(simpleTask.getQuestion()).build();
        long answer = (long) expression.evaluate();
        long botAnswer = Long.parseLong(simpleTask.getAnswer());
        Assertions.assertTrue(simpleTask.getDifficulty() == Difficulty.EXTREME &&
                answer == botAnswer && simpleTask.getQuestion() != null);
    }

    @Test
    void testGetAdditionalCode() {
        Task additionalCodeTask = taskGenerator.getAdditionalCode();
        int answer = Integer.parseInt(additionalCodeTask.getAnswer());
        Assertions.assertTrue(Math.abs(answer) <= 7 && additionalCodeTask.getAnswer() != null &&
                additionalCodeTask.getDifficulty() == Difficulty.EASY);
    }
}