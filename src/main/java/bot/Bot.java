package bot;

public class Bot {
    private Long answer;
    private final Generator generator = new Generator();

    public String getQuestion() throws Exception {
        Pair<Long, String> question = generator.question();
        answer = question.getFirst();
        return question.getSecond();
    }
    public void setGenerator(long min, long max) throws Exception {
        generator.questionOptions(min, max, "+");
    }
    public boolean checkAnswer(long suspect) {
        return answer == suspect;
    }
}
