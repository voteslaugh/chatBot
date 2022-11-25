package bot;

import bot.api.BotReply;
import bot.api.ChatUpdate;
import bot.configs.BotConfig;
import bot.functions.Button;
import bot.functions.Command;
import bot.functions.FunctionReply;

import java.util.ArrayList;
import java.util.List;

public class ChatBot implements Bot {
    private List<String> cache = new ArrayList<>();
    private BotConfig config;

    public ChatBot(BotConfig config) {
        this.config = config;
    }
    private BotReply preProcess(ChatUpdate chatUpdate) {
        List<List<Button>> rowsKeyboard = new ArrayList<>();
        List<Button> rowKeyboard = new ArrayList<>();
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatUpdate.getChatId());
        FunctionReply functionReply = new FunctionReply();

        for (Command command : config.textHandler().getCommandHandler().getListCommand()) {
            if (command.isAddToKeyBoard()) {
                rowKeyboard.add(new Button(command.getName()));
            }
        }
        rowsKeyboard.add(rowKeyboard);
        functionReply.setKeyboard(rowsKeyboard);
        functionReply.setText("Я готов к работе!");
        botReply.setFunctionReply(functionReply);
        return botReply;
    }
    @Override
    public BotReply reply(ChatUpdate chatUpdate) {
        String chatId = chatUpdate.getChatId();
        if (!cache.contains(chatId)) {
            cache.add(chatId);
            return preProcess(chatUpdate);
        }
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatId);
        ChatHistory chatHistory = config.dataBase().getChatHistory(chatId);

        if (chatHistory == null) {
            chatHistory = new ChatHistory();
        }
        FunctionReply functionReply = config.textHandler().process(chatHistory, chatUpdate.getMessage());
        botReply.setFunctionReply(functionReply);
        config.dataBase().save(chatId, chatHistory);
        return botReply;
    }
}
