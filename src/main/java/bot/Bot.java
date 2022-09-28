package bot;

public class Bot {
    private Long answer;
    private final Generator generator = new Generator();

    public String getQuestion() throws Exception {
        Pair<Long, String> question = generator.question();
        answer = question.getFirst();
        return question.getSecond();
    }
    public void setGeneratorMin(long min) {
        generator.questionOptionForMin(min);
    }
    public void setGeneratorMax(long max) {
        generator.questionOptionForMax(max);
    }
    public void setGeneratorOperation(String operation) throws Exception {
        generator.questionOptionForOperation(operation);
    }
    public boolean checkAnswer(long suspect) {
        return answer == suspect;
    }
}
