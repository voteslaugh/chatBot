package components;

import bot.*;
import bot.api.BotReply;
import bot.api.ChatUpdate;
import bot.functions.api.FunctionReply;
import bot.models.ChatHistory;
import bot.configs.BotConfig;
import bot.functions.components.*;
import bot.functions.services.*;

public class MathBot implements Bot {
    private BotConfig config;

    public MathBot(BotConfig config) {
        this.config = config;
    }
    private FunctionReply process(ChatHistory chatHistory, String text) {
        CommandHandler commandHandler = config.commandHandler();
        String nameLastCommand = chatHistory.getNameCommand();
        Command lastCommand = commandHandler.getCommand(nameLastCommand);
        Command command = commandHandler.getCommand(text);
        if (command == null || !(command.isCompatible(lastCommand))) {
            return lastCommand.getFunction().doFunction(chatHistory, text);
        } else {
            chatHistory.setNameCommand(text);
            return command.getFunction().doFunction(chatHistory, null);
        }
        // Могут возникнуть ошибки, если:
        //      1) lastCommand == null
        //      2) getFunction() -> null
    }
    @Override
    public BotReply reply(ChatUpdate chatUpdate) {
        String chatId = chatUpdate.getChatId();
        String text = chatUpdate.getText();
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatUpdate.getChatId());
        ChatHistory chatHistory = config.dataBase().getChatHistory(chatId);
        if (chatHistory == null) {
            chatHistory = new ChatHistory();
            chatHistory.setNameCommand(config.commandHandler().getDefaultNameCommand());
        }
        FunctionReply functionReply = process(chatHistory, text);
        botReply.setText(functionReply.getText());
        config.dataBase().save(chatId, chatHistory);
        return botReply;
    }
}
