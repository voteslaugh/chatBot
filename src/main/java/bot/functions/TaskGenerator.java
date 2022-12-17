package bot.functions;
import static bot.functions.Randomizer.getLongInRange;

public class TaskGenerator {
    private Operation operation;

    public Operation getOperation() {
        return operation;
    }

    public void setRandom() {
        switch ((int) getLongInRange(1, 4)) {
            case 1 -> operation = Operation.SUM;
            case 2 -> operation = Operation.DIFF;
            case 3 -> operation = Operation.MULTIPLICATION;
            case 4 -> operation = Operation.DIVISION;
        };
    }

    private long getMin(Difficulty difficulty) {
        return switch (difficulty){
            case EASY -> -10;
            case MEDIUM -> -100;
            case HARD -> -1000;
            case EXTREME -> -1000000;
        };
    }

    private long getMax(Difficulty difficulty) {
        return Math.abs(getMin(difficulty));
    }

    public Difficulty increaseDifficulty(Difficulty difficulty)
    {
        return switch (difficulty) {
            case EASY -> Difficulty.MEDIUM;
            case MEDIUM -> Difficulty.HARD;
            case HARD -> Difficulty.EXTREME;
            case EXTREME -> null;
        };
    }

    public Difficulty reduceDifficulty(Difficulty difficulty){
        return switch (difficulty) {
            case EASY -> null;
            case MEDIUM -> Difficulty.EASY;
            case HARD -> Difficulty.MEDIUM;
            case EXTREME -> Difficulty.HARD;
        };
    }

    public Task getSimpleTask(Difficulty difficulty)  {
        setRandom();
        long firstNumber = getLongInRange(getMin(difficulty), getMax(difficulty));
        long secondNumber = getLongInRange(getMin(difficulty), getMax(difficulty));
        String answer = null, question;
        if (operation == Operation.DIVISION) {
            if (Math.abs(firstNumber) < Math.abs(secondNumber)) {
                firstNumber = (firstNumber & secondNumber) + (firstNumber | secondNumber);
                secondNumber = firstNumber + (~secondNumber) + 1;
                firstNumber = firstNumber + (~secondNumber) + 1;
            }
            if (secondNumber < 0) question = "("+secondNumber+")";
            else question = Long.toString(secondNumber);
            try {
                if (firstNumber < 0) firstNumber = -Math.abs(firstNumber) - firstNumber % secondNumber;
                else firstNumber = firstNumber - firstNumber % secondNumber;

                answer = Long.toString(firstNumber / secondNumber);
            } catch (ArithmeticException e) {
                answer = String.valueOf(firstNumber);
                secondNumber = 1;
            }
            question = firstNumber + " / " + question;
        }
        else {
            if (secondNumber < 0) question = "("+secondNumber+")";
            else question = Long.toString(secondNumber);
            if (operation == Operation.SUM) {
                answer = Long.toString(firstNumber + secondNumber);
                question = firstNumber + " + " + question;
            }
            else if (operation == Operation.DIFF) {
                answer = Long.toString(firstNumber - secondNumber);
                question = firstNumber + " - " + question;
            }
            else if (operation == Operation.MULTIPLICATION) {
                answer = Long.toString(firstNumber * secondNumber);
                question = firstNumber + " * " + question;
            }
        }
        return new Task(question, answer, difficulty);
    }

    public Task getAdditionalCode()
    {
        long answer = getLongInRange(-7, 7);
        if (answer >= 0) {
            String binaryAnswer = Long.toBinaryString(answer);
            binaryAnswer = "0".repeat(4 - binaryAnswer.length()) + binaryAnswer;
            return new Task(binaryAnswer, Long.toString(answer), Difficulty.EASY);
        } else{
            String binaryAnswer = Long.toBinaryString(Math.abs(answer));
            binaryAnswer = "0".repeat(4 - binaryAnswer.length()) + binaryAnswer;
            StringBuilder inverted = new StringBuilder();
            for (int i=0; i<binaryAnswer.length(); ++i) {
                inverted.append((Byte.parseByte(String.valueOf(binaryAnswer.charAt(i))) + 1) % 2);
            }
            String invertedSummed = "";
            int i=inverted.length()-1;
            for (; i>=0; --i){

                if(String.valueOf(inverted.charAt(i)).equals("1")) {
                    invertedSummed = "0" + invertedSummed;
                } else {
                    invertedSummed = "1" + invertedSummed;
                    break;
                }
            }
            invertedSummed = inverted.substring(0, i) + invertedSummed;
            if (invertedSummed.length() == 5)
                invertedSummed = invertedSummed.substring(0, 3);
            return new Task(invertedSummed, Long.toString(answer), Difficulty.EASY);
        }

    }

}
