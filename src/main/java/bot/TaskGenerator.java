package bot;

public class TaskGenerator {
    private long min=0, max=10;
    private String operation="+";

    public void questionOptionForMin(long min) {
        this.min = min;
    }
    public void questionOptionForMax(long max) {
        this.max = max;
    }
    public void questionOptionForOperation(String operation) throws Exception {
        if(!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/"))
            throw new Exception("Not operation spotted");
        else
            this.operation = operation;
    }

    private static long longInRange(long min, long max){
        return (long) (Math.random()*((max-min)+1))+min;
    }

    private long getAnswer(long a, long b) { //заглушка
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

    public Task getTask()  {
        long a = longInRange(min, max);
        long b = longInRange(min, max);
        String answer = Long.toString(getAnswer(a, b));
        String question = a+operation+b+'=';
        return new Task(question, answer);
    }

}
