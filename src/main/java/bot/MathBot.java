package bot;

import java.util.Objects;

public class MathBot {
    DataBase dataBase = new DataBase();
    private final TaskGenerator taskGenerator = new TaskGenerator();
    
    public BotReply reply(ChatUpdate chatUpdate) {
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatUpdate.getChatId());

        UserChatHistory userChatHistory = dataBase.getChatHistory(chatUpdate.getUserId());
        BotStatus status = userChatHistory.getLastBotStatus();

        if (Objects.equals(chatUpdate.getText(), "/sleep")) {
            status = BotStatus.SLEEPING;
            botReply.setText("(￣o￣) zzZZzzZZ");
            userChatHistory = dataBase.getChatHistory(chatUpdate.getUserId());
            userChatHistory.setLastBotStatus(status);
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
                    userChatHistory.setLastTask(taskGenerator.getSimpleTask());
                    botReply.setText("Answer the question...\n" + userChatHistory.getLastTask().getQuestion());
                }
                else if (Objects.equals(chatUpdate.getText(), "/bintest")) {
                    status = BotStatus.BINARY_TESTING;
                    userChatHistory.setLastTask(taskGenerator.getAdditionalCode());
                    botReply.setText("Answer the question...\n" + userChatHistory.getLastTask().getQuestion());
                }
                else {
                    botReply.setText("Unknown command :(");
                }
                break;
            case SIMPLE_TESTING:
                if (Objects.equals(chatUpdate.getText(), "/stop")) {
                    status = BotStatus.WAITING_COMMAND;
                    botReply.setText("I'm waiting command...");
                    userChatHistory = dataBase.getChatHistory(chatUpdate.getUserId());
                    userChatHistory.setLastBotStatus(status);
                    return botReply;
                }
                if(Objects.equals(chatUpdate.getText(), userChatHistory.getLastTask().getAnswer())) {
                    userChatHistory.setLastTask(taskGenerator.getSimpleTask());
                    botReply.setText("Excellently!\n" + userChatHistory.getLastTask().getQuestion());
                } else {
                    botReply.setText("Wrong answer. Try again!\n" + userChatHistory.getLastTask().getQuestion());
                }
                break;
            case BINARY_TESTING:
                if (Objects.equals(chatUpdate.getText(), "/stop")) {
                    status = BotStatus.WAITING_COMMAND;
                    botReply.setText("I'm waiting command...");
                    userChatHistory = dataBase.getChatHistory(chatUpdate.getUserId());
                    userChatHistory.setLastBotStatus(status);
                    return botReply;
                }
                if(Objects.equals(chatUpdate.getText(), userChatHistory.getLastTask().getAnswer())) {
                    userChatHistory.setLastTask(taskGenerator.getAdditionalCode());
                    botReply.setText("Excellently!\n" + userChatHistory.getLastTask().getQuestion());
                } else {
                    botReply.setText("Wrong answer. Try again!\n" + userChatHistory.getLastTask().getQuestion());
                }
                break;
        }
        userChatHistory.setLastBotStatus(status);
        return botReply;
    }
}
