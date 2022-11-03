package components;

import bot.*;
import bot.functions.*;

public class MathBot implements Bot {
    private TaskGenerator taskGenerator;
    private DataBase dataBase;

    public MathBot(TaskGenerator taskGenerator, DataBase dataBase) {
        this.taskGenerator = taskGenerator;
        this.dataBase = dataBase;
    }

    @Override
    public BotReply reply(ChatUpdate chatUpdate) {
        String chatId = chatUpdate.getChatId();
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatUpdate.getChatId());
        ChatHistory chatHistory = dataBase.getChatHistory(chatId);
        Function function;
        if (chatHistory == null) {
            chatHistory = new ChatHistory(new Sleep());
        }
        function = chatHistory.getLastFunction();
        Status status = function.runFunction(chatUpdate.getText());
        if (status != function.getStatus()) {
            switch (status) {
                case SLEEPING -> function = new Sleep();
                case WAITING_COMMAND -> function = new WaitCommand();
                case BINARY_TESTING -> function = new BinTest(taskGenerator);
                case EASY_TESTING -> function = new EasyTest(taskGenerator);
            }
        }
        botReply.setText(function.getFunctionReply().getText());
        chatHistory.setLastFunction(function);
        dataBase.save(chatId, chatHistory);
        return botReply;
    }
}
