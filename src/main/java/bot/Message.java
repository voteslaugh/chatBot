package bot;

public class Message {
    private String text = null;
    private String warning = null;
    private Task task = null;

    public String getText() {
        return text;
    }

    public String getWarning() {
        return warning;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
