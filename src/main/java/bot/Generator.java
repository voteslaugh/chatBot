package bot;

public class Generator {
    long min=0, max=10;
    String operation="+";
    public void questionOptions(long min, long max, String operation) throws Exception {
        this.min = min;
        this.max = max;
        if(!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/"))
            throw new Exception("Not operation spotted");
        else
            this.operation = operation;
    }

    private static long longInRange(long min, long max){
        return (long) (Math.random()*((max-min)+1))+min;
    }

    private long getAnswer(long a, long b) throws Exception {
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
        }
        throw new Exception("Not operation spotted");
    }

    public Pair<Long, String> question() throws Exception {
        long a = longInRange(min, max);
        long b = longInRange(min, max);
        long answer = getAnswer(a, b);
        String string = a+operation+b;
        return (Pair<Long, String>) new Pair(answer, string);
    }

}
