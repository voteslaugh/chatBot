package bot.functions;

public class Task {
    private String question;
    private String answer;
    private Difficulty difficulty;

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Task(String question, String answer, Difficulty difficulty) {
        this.question = question;
        this.answer = answer;
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }
}
