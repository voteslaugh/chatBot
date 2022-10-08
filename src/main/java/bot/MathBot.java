package bot;

import java.util.Objects;

public class MathBot {
    private BotStatus status = BotStatus.SLEEPING;
    private final TaskGenerator taskGenerator = new TaskGenerator();

    private BotReply getBotReplay(ChatUpdate chatUpdate, String text) { // Временно
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatUpdate.getChatId(), status);
        Message message = new Message();
        message.setText(text);
        botReply.setMessage(message);
        return  botReply;
    }
    public BotReply reply(ChatUpdate chatUpdate) {
        // взаимодействие с БД
        if (Objects.equals(chatUpdate.getText(), "/exit")) {
            this.status = BotStatus.SLEEPING;
            return getBotReplay(chatUpdate, "I'm sleeping!\n");
        }
        switch (status) { // Временно
            case SLEEPING:
                if (Objects.equals(chatUpdate.getText(), "/start")) {
                    this.status = BotStatus.WAITING_COMMAND;
                    return getBotReplay(chatUpdate, "I'm waiting command!\n");
                } else {
                    return getBotReplay(chatUpdate, "I'm sleeping!\n");
                }
            case WAITING_COMMAND:
                if (Objects.equals(chatUpdate.getText(), "/test")) {
                    this.status = BotStatus.TESTING_FOR_ONE;
                    BotReply botReply = getBotReplay(chatUpdate, "Answer the question...\n");
                    botReply.getMessage().setTask(taskGenerator.getTask());
                    return botReply;
                } else {
                    return getBotReplay(chatUpdate, "Unknown command :(\n");
                }
            case TESTING_FOR_ONE:
                BotReply botReply;
                if(Objects.equals(chatUpdate.getText(), taskGenerator.getLastTask().getAnswer())) {
                    botReply = getBotReplay(chatUpdate, "Excellently!\n");
                    botReply.getMessage().setTask(taskGenerator.getTask());
                } else {
                    botReply = getBotReplay(chatUpdate, "Wrong answer. Try again!\n");
                    botReply.getMessage().setTask(taskGenerator.getLastTask());
                }
                return botReply;
        }
        return null;
    }
    public BotStatus getStatus() {
        return this.status;
    }
}
