package bot.tests;

import bot.functions.Difficulty;
import bot.functions.Task;
import bot.functions.TaskGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    @Test
    void testGetEasySimpleTask() {
        Difficulty difficulty = Difficulty.EASY;
        Task simpleTask = taskGenerator.getSimpleTask(difficulty);
        int maxValue = 100;
        int answer =  Integer.parseInt(simpleTask.getAnswer());
        Assertions.assertTrue(simpleTask.getDifficulty() == Difficulty.EASY &&
                Math.abs(answer) <= maxValue && simpleTask.getQuestion() != null);
    }

    @Test
    void testGetMediumSimpleTask() {
        Difficulty difficulty = Difficulty.MEDIUM;
        Task simpleTask = taskGenerator.getSimpleTask(difficulty);
        int maxValue = 10000;
        int answer =  Integer.parseInt(simpleTask.getAnswer());
        Assertions.assertTrue(simpleTask.getDifficulty() == Difficulty.MEDIUM &&
                Math.abs(answer) <= maxValue && simpleTask.getQuestion() != null);
    }

    @Test
    void testGetHardSimpleTask() {
        Difficulty difficulty = Difficulty.HARD;
        Task simpleTask = taskGenerator.getSimpleTask(difficulty);
        int maxValue = 1000000;
        int answer =  Integer.parseInt(simpleTask.getAnswer());
        Assertions.assertTrue(simpleTask.getDifficulty() == Difficulty.HARD &&
                Math.abs(answer) <= maxValue && simpleTask.getQuestion() != null);
    }

    @Test
    void testGetExtremeSimpleTask() {
        Difficulty difficulty = Difficulty.EXTREME;
        Task simpleTask = taskGenerator.getSimpleTask(difficulty);
        long maxValue = 1000000000000L;
        long answer =  Long.parseLong(simpleTask.getAnswer());
        Assertions.assertTrue(simpleTask.getDifficulty() == Difficulty.EXTREME &&
                Math.abs(answer) <= maxValue && simpleTask.getQuestion() != null);
    }

    @Test
    void testGetAdditionalCode() {
        Task additionalCodeTask = taskGenerator.getAdditionalCode();
        int answer = Integer.parseInt(additionalCodeTask.getAnswer());
        Assertions.assertTrue(Math.abs(answer) <= 7 && additionalCodeTask.getAnswer() != null &&
                additionalCodeTask.getDifficulty() == Difficulty.EASY);
    }
}