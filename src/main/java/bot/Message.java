package bot;

public class Message {
    private String text = null;
    private String warning = null;
    private String question = null;

    public String getText() {
        return text;
    }

    public String getWarning() {
        return warning;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
