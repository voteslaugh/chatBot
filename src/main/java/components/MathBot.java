package components;

import bot.*;
import bot.configs.FunctionConfig;
import bot.functions.Sleep;

public class MathBot implements Bot {
    private FunctionConfig functionConfig;
    private DataBase dataBase;

    public MathBot(FunctionConfig functionConfig, DataBase dataBase) {
        this.functionConfig = functionConfig;
        this.dataBase = dataBase;
    }

    @Override
    public BotReply reply(ChatUpdate chatUpdate) {
        String chatId = chatUpdate.getChatId();
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatUpdate.getChatId());
        ChatHistory chatHistory = dataBase.getChatHistory(chatId);
        if (chatHistory == null) {
            chatHistory = new ChatHistory(new Sleep(functionConfig));
        }
        chatHistory.setLastFunction(chatHistory.getLastFunction().doFunction(chatUpdate));
        botReply.setText(chatHistory.getLastFunction().getFunctionReply().getText());
        dataBase.save(chatId, chatHistory);
        return botReply;
    }
}
