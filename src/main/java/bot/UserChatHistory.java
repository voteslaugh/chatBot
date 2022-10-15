package bot;

public class UserChatHistory {
    private BotStatus lastBotStatus;
    private Task lastTask = null;

    UserChatHistory() {}

    public UserChatHistory(BotStatus status) {
        lastBotStatus = status;
    }

    public Task getLastTask() {
        return lastTask;
    }

    public BotStatus getLastBotStatus() {
        return lastBotStatus;
    }

    public void setLastBotStatus(BotStatus lastBotStatus) {
        this.lastBotStatus = lastBotStatus;
    }

    public void setLastTask(Task lastTask) {
        this.lastTask = lastTask;
    }
}
