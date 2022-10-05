package bot;

public class BotReply extends ChatUpdate{
    private Task task = null;
    private String warningMessage = null;

    public BotReply(String userId, String message) {
        super(userId, message);
    }
    public BotReply(String userId, String message, Task task) {
        super(userId, message);
        this.task = task;
    }
    public BotReply(String userId, String message, Task task, String warningMessage) {
        super(userId, message);
        this.warningMessage = warningMessage;
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
