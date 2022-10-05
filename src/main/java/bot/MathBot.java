package bot;

import java.util.Objects;

public class MathBot {
    private BotStatus status = BotStatus.SLEEPING;
    private TaskGenerator taskGenerator = new TaskGenerator(); // нужно изменить название

    public BotReply reply(ChatUpdate chatUpdate) {
        switch (this.status) {
            case SLEEPING:
                if (Objects.equals(chatUpdate.getMessage(), "/start")) {
                    this.status = BotStatus.WAITING_COMMAND;
                    return new BotReply(chatUpdate.getUserId(), "I'm waiting command!\n");
                }
                else
                    return new BotReply(chatUpdate.getUserId(), "I'm sleeping!\n");
            case WAITING_COMMAND:
                if (Objects.equals(chatUpdate.getMessage(), "/test_for_one")) {
                    this.status = BotStatus.TESTING_FOR_ONE;
                    return new BotReply(chatUpdate.getUserId(), "Answer the question...\n", taskGenerator.getTask());
                } else {
                    return new BotReply(chatUpdate.getUserId(), "Unknown command :(\n");
                }
            case TESTING_FOR_ONE:
                if (Objects.equals(chatUpdate.getMessage(), taskGenerator.getLastTask().getAnswer())) {
                    return new BotReply(chatUpdate.getUserId(), "Excellently!\n", taskGenerator.getTask());
                } else {
                    return new BotReply(chatUpdate.getUserId(), "Wrong answer. Try again!\n", taskGenerator.getLastTask());
                }
        }
        if (Objects.equals(chatUpdate.getMessage(), "Hello")) {
            return new BotReply(chatUpdate.getUserId(), "Hello, I'm MathBot!\n");
        } else {
            return new BotReply(chatUpdate.getUserId(), "error... :(\n");
        }
    }
    public BotStatus getStatus() {
        return this.status;
    }
}
