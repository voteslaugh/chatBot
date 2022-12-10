package bot.functions;

public class TaskGenerator {
    private Difficulty difficulty = Difficulty.EASY;
    private Operation operation;

    private long longInRange(long min, long max){
        return (long) (Math.random()*((max-min)+1))+min;
    }

    public void setRandom() {
        int index = (int) (Math.random()*((4-1)+1)+1);
        switch (index) {
            case 1 -> operation = Operation.SUM;
            case 2 -> operation = Operation.DIFF;
            case 3 -> operation = Operation.MULTIPLICATION;
            case 4 -> operation = Operation.DIVISION;
        };
    }

    private long getMin() {
        return switch (this.difficulty){
            case EASY -> -10;
            case MEDIUM -> -100;
            case HARD -> -1000;
            case EXTREME -> -1000000;
        };
    }

    private long getMax() {
        return Math.abs(getMin());
    }

    public void increaseDifficulty()
    {
        switch (this.difficulty) {
            case EASY -> this.difficulty = Difficulty.MEDIUM;
            case MEDIUM -> this.difficulty = Difficulty.HARD;
            case HARD -> this.difficulty = Difficulty.EXTREME;
        }
    }

    public void reduceDifficulty(){
        switch (this.difficulty) {
            case MEDIUM -> this.difficulty = Difficulty.EASY;
            case HARD -> this.difficulty = Difficulty.MEDIUM;
            case EXTREME -> this.difficulty = Difficulty.HARD;
        }
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Task getSimpleTask()  {
        setRandom();
        long firstNumber = longInRange(getMin(), getMax());
        long secondNumber = longInRange(getMin(), getMax());
        String answer = null, question;
        if (operation == Operation.DIVISION) {
            if (Math.abs(firstNumber) < Math.abs(secondNumber)) {
                firstNumber = (firstNumber & secondNumber) + (firstNumber | secondNumber);
                secondNumber = firstNumber + (~secondNumber) + 1;
                firstNumber = firstNumber + (~secondNumber) + 1;
            }
            if (secondNumber < 0) question = "("+secondNumber+")";
            else question = Long.toString(secondNumber);

            if (firstNumber < 0) firstNumber = - Math.abs(firstNumber) - firstNumber % secondNumber;
            else firstNumber = firstNumber - firstNumber % secondNumber;

            answer = Long.toString(firstNumber / secondNumber);
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
        return new Task(question, answer);
    }

    public Task getAdditionalCode()
    {
        long answer = longInRange(-7, 7);
        if (answer >= 0) {
            String binaryAnswer = Long.toBinaryString(answer);
            binaryAnswer = "0".repeat(4 - binaryAnswer.length()) + binaryAnswer;
            return new Task(binaryAnswer, Long.toString(answer));
        } else{
            String binaryAnswer = Long.toBinaryString(Math.abs(answer));
            binaryAnswer = "0".repeat(4 - binaryAnswer.length()) + binaryAnswer;
            String inverted = "";
            for (int i=0; i<binaryAnswer.length(); ++i) {
                inverted += (Byte.parseByte(String.valueOf(binaryAnswer.charAt(i))) + 1) % 2;
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
            return new Task(invertedSummed, Long.toString(answer));
        }

    }

}
