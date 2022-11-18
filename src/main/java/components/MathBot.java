package components;

import bot.*;
import bot.api.BotReply;
import bot.api.ChatUpdate;
import bot.components.CommandHandler;
import bot.functions.api.FunctionReply;
import bot.models.ChatHistory;
import bot.configs.BotConfig;
import bot.functions.components.*;

import java.util.Objects;

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
        botReply.setText(functionReply.getText());
        config.dataBase().save(chatId, chatHistory);
        return botReply;
    }
}
