package bot;

public class TaskGenerator {
    private long min=0, max=10;
    private String operation="+";

    public void generatorOptionForMin(long min) {
        this.min = min;
    }
    public void generatorOptionForMax(long max) {
        this.max = max;
    }
    public void generatorOperationOption(String operation) throws Exception {
        if(!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/"))
            throw new Exception("Not operation spotted");
        else
            this.operation = operation;
    }

    private static long longInRange(long min, long max){
        return (long) (Math.random()*((max-min)+1))+min;
    }

    private long getSimpleTaskAnswer(long a, long b) { //заглушка
        switch (operation)
        {
            case("+"):
                return a+b;
            case("-"):
                return a-b;
            case("*"):
                return a*b;
            case("/"):
                return a/b;
            default:
                return 1;
        }
    }

    public Task getSimpleTask()  {
        long a = longInRange(min, max);
        long b = longInRange(min, max);
        String answer = Long.toString(getSimpleTaskAnswer(a, b));
        String question = a+operation+b+'=';
        return new Task(question, answer);
    }

    public Task getAdditionalCode()
    {
        long answer = longInRange(-7, 8);
        if (answer >= 0) {
            String binaryAnswer = Long.toBinaryString(answer);
            binaryAnswer = "0".repeat(4 - binaryAnswer.length()) + binaryAnswer + "=";
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
            return new Task(invertedSummed + "=", Long.toString(answer));
        }

    }

}
