package components;

import bot.Bot;
import bot.api.BotReply;
import bot.api.ChatUpdate;
import bot.configs.BotConfig;
import bot.functions.api.FunctionReply;
import bot.models.ChatHistory;

public class MathBot implements Bot {
    private BotConfig config;

    public MathBot(BotConfig config) {
        this.config = config;
    }
    @Override
    public BotReply reply(ChatUpdate chatUpdate) {
        String chatId = chatUpdate.getChatId();
        String text = chatUpdate.getText();
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatUpdate.getChatId());
        ChatHistory chatHistory = config.dataBase().getChatHistory(chatId);
        if (chatHistory == null) {
            chatHistory = new ChatHistory();
            chatHistory.setNameCommand(config.textHandler().getCommandHandler().getDefaultNameCommand());
        }
        FunctionReply functionReply = config.textHandler().process(chatHistory, text);
        botReply.setFunctionReply(functionReply);
        config.dataBase().save(chatId, chatHistory);
        return botReply;
    }
}
