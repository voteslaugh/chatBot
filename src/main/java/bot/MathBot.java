package bot;

import java.util.Objects;

public class MathBot {
    DataBase dataBase = new DataBase();
    private BotStatus status = BotStatus.SLEEPING;
    private final TaskGenerator taskGenerator = new TaskGenerator();
    
    public BotReply reply(ChatUpdate chatUpdate) {
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatUpdate.getChatId());

        ChatHistory chatHistory = dataBase.getChatHistory(chatUpdate.getUserId());
        this.status = chatHistory.getLastBotStatus();

        if (Objects.equals(chatUpdate.getText(), "/exit")) {
            this.status = BotStatus.SLEEPING;
            botReply.setText("I'm sleeping!\n");
            chatHistory = dataBase.getChatHistory(chatUpdate.getUserId());
            chatHistory.setLastBotStatus(this.status);
            return botReply;
        }
        switch (status) {
            case SLEEPING:
                if (Objects.equals(chatUpdate.getText(), "/start")) {
                    this.status = BotStatus.WAITING_COMMAND;
                    botReply.setText("I'm waiting command...");
                }
                break;
            case WAITING_COMMAND:
                if (Objects.equals(chatUpdate.getText(), "/test")) {
                    this.status = BotStatus.TESTING;
                    chatHistory.setLastTask(taskGenerator.getTask());
                    botReply.setText("Answer the question...\n" + chatHistory.getLastTask().getQuestion());
                } else {
                    botReply.setText("Unknown command :(");
                }
                break;
            case TESTING:
                if(Objects.equals(chatUpdate.getText(), chatHistory.getLastTask().getAnswer())) {
                    chatHistory.setLastTask(taskGenerator.getTask());
                    botReply.setText("Excellently!\n" + chatHistory.getLastTask().getQuestion());
                } else {
                    botReply.setText("Wrong answer. Try again!\n" + chatHistory.getLastTask().getQuestion());
                }
                break;
        }
        chatHistory.setLastBotStatus(this.status);
        return botReply;
    }
    public BotStatus getStatus() {
        return this.status;
    }
}
