package bot;

import java.util.Objects;

public class MathBot {
    DataBase dataBase = new DataBase();
    private final TaskGenerator taskGenerator = new TaskGenerator();
    
    public BotReply reply(ChatUpdate chatUpdate) {
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatUpdate.getChatId());

        ChatHistory chatHistory = dataBase.getChatHistory(chatUpdate.getUserId());
        BotStatus status = chatHistory.getLastBotStatus();

        if (Objects.equals(chatUpdate.getText(), "/exit")) {
            status = BotStatus.SLEEPING;
            botReply.setText("I'm sleeping!");
            chatHistory = dataBase.getChatHistory(chatUpdate.getUserId());
            chatHistory.setLastBotStatus(status);
            return botReply;
        }
        switch (status) {
            case SLEEPING:
                if (Objects.equals(chatUpdate.getText(), "/start")) {
                    status = BotStatus.WAITING_COMMAND;
                    botReply.setText("I'm waiting command...");
                } else {
                    botReply.setText("I'm sleeping!");
                }
                break;
            case WAITING_COMMAND:
                if (Objects.equals(chatUpdate.getText(), "/test")) {
                    status = BotStatus.TESTING;
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
        chatHistory.setLastBotStatus(status);
        return botReply;
    }
}
