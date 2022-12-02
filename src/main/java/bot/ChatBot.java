package bot;

import bot.api.BotReply;
import bot.api.ChatUpdate;
import bot.configs.BotConfig;
import bot.functions.Button;
import bot.functions.Command;
import bot.functions.Data;
import java.util.ArrayList;
import java.util.List;

public class ChatBot implements Bot {
    private List<String> cache = new ArrayList<>();
    private BotConfig config;

    public List<Command> getMenuCommands() {
        List<Command> commands = new ArrayList<>();
        for (Command command : config.textHandler().getCommandHandler().getListCommand()) {
            if (command.isAddToMenu()) {
                commands.add(command);
            }
        }
        return commands;
    }

    public ChatBot(BotConfig config) {
        this.config = config;
    }
    private BotReply preprocess(ChatUpdate chatUpdate) {
        List<List<Button>> rowsKeyboard = new ArrayList<>();
        List<Button> rowKeyboard = new ArrayList<>();
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatUpdate.getChatId());
        Data data = new Data();

        for (Command command : config.textHandler().getCommandHandler().getListCommand()) {
            if (command.isAddToKeyBoard()) {
                rowKeyboard.add(new Button(command.getName()));
            }
        }
        rowsKeyboard.add(rowKeyboard);
        data.setKeyboard(rowsKeyboard);
        data.setText("Я готов к работе!");
        botReply.setData(data);
        return botReply;
    }
    @Override
    public BotReply reply(ChatUpdate chatUpdate) {
        String chatId = chatUpdate.getChatId();
        if (!cache.contains(chatId)) {
            cache.add(chatId);
            return preprocess(chatUpdate);
        }
        BotReply botReply = new BotReply(chatUpdate.getUserId(), chatId);
        ChatHistory chatHistory = config.dataBase().getChatHistory(chatId);

        if (chatHistory == null) {
            chatHistory = new ChatHistory();
        }
        botReply.setData(config.textHandler().process(chatHistory, chatUpdate.getMessage()));
        config.dataBase().save(chatId, chatHistory);
        return botReply;
    }
}
