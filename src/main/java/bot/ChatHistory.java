package bot;

public class ChatHistory {
    private BotStatus lastBotStatus;
    private Task lastTask = null;

    ChatHistory() {}

    public ChatHistory(BotStatus status) {
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
