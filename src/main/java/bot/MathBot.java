package bot;

import java.util.Objects;

public class MathBot implements Bot {
    private DataBase dataBase;
    private final TaskGenerator taskGenerator = new TaskGenerator();

    public MathBot(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public BotReply reply(ChatUpdate chatUpdate) {
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatUpdate.getChatId());
        ChatHistory chatHistory = dataBase.getChatHistory(chatUpdate.getChatId());
        BotStatus status = chatHistory.getLastBotStatus();

        if (Objects.equals(chatUpdate.getText(), "/sleep")) {
            status = BotStatus.SLEEPING;
            botReply.setText("(￣o￣) zzZZzzZZ");
            chatHistory = dataBase.getChatHistory(chatUpdate.getUserId());
            chatHistory.setLastBotStatus(status);
            return botReply;
        } else if(Objects.equals(chatUpdate.getText(), "/help")){
            botReply.setText("""
                    (￣o￣) zzZZzzZZ
                    Это бот, который поможет тебе изучать математику.
                    Для того, чтобы разбудить его, напиши "/start".
                    Команда "/easytest" поможет тебе тренироваться в простом счёте.
                    Команда "/bintest" научит ориентироваться в дополнительном коде.
                    Команда "/stop", если хочешь закончить тестирование.
                    Если ты закончил, отправь бота досыпать, введя "/sleep".
                    """);
            return botReply;
        }
        switch (status) {
            case SLEEPING:
                if (Objects.equals(chatUpdate.getText(), "/start")) {
                    status = BotStatus.WAITING_COMMAND;
                    botReply.setText("I'm waiting command...");
                } else {
                    botReply.setText("(￣o￣) zzZZzzZZ");
                }
                break;
            case WAITING_COMMAND:
                if (Objects.equals(chatUpdate.getText(), "/easytest")) {
                    status = BotStatus.SIMPLE_TESTING;
                    chatHistory.setLastTask(taskGenerator.getSimpleTask());
                    botReply.setText("Answer the question...\n" + chatHistory.getLastTask().getQuestion());
                }
                else if (Objects.equals(chatUpdate.getText(), "/bintest")) {
                    status = BotStatus.BINARY_TESTING;
                    chatHistory.setLastTask(taskGenerator.getAdditionalCode());
                    botReply.setText("Answer the question...\n" + chatHistory.getLastTask().getQuestion());
                }
                else {
                    botReply.setText("Unknown command :(");
                }
                break;
            case SIMPLE_TESTING:
                if (Objects.equals(chatUpdate.getText(), "/stop")) {
                    status = BotStatus.WAITING_COMMAND;
                    botReply.setText("I'm waiting command...");
                    chatHistory = dataBase.getChatHistory(chatUpdate.getUserId());
                    chatHistory.setLastBotStatus(status);
                    return botReply;
                }
                if(Objects.equals(chatUpdate.getText(), chatHistory.getLastTask().getAnswer())) {
                    chatHistory.setLastTask(taskGenerator.getSimpleTask());
                    botReply.setText("Excellently!\n" + chatHistory.getLastTask().getQuestion());
                } else {
                    botReply.setText("Wrong answer. Try again!\n" + chatHistory.getLastTask().getQuestion());
                }
                break;
            case BINARY_TESTING:
                if (Objects.equals(chatUpdate.getText(), "/stop")) {
                    status = BotStatus.WAITING_COMMAND;
                    botReply.setText("I'm waiting command...");
                    chatHistory = dataBase.getChatHistory(chatUpdate.getUserId());
                    chatHistory.setLastBotStatus(status);
                    return botReply;
                }
                if(Objects.equals(chatUpdate.getText(), chatHistory.getLastTask().getAnswer())) {
                    chatHistory.setLastTask(taskGenerator.getAdditionalCode());
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
