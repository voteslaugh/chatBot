package bot;

import bot.api.BotReply;
import bot.api.ChatUpdate;
import bot.configs.BotConfig;
import bot.functions.Command;
import java.util.ArrayList;
import java.util.List;

public class ChatBot implements Bot {
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

    @Override
    public BotReply reply(ChatUpdate chatUpdate) {
        String chatId = chatUpdate.getChatId();
        BotReply botReply = new BotReply(null, chatId);
        ChatHistory chatHistory = config.chatHistoryRepository().getChatHistory(chatId);

        if (chatHistory == null) {
            chatHistory = new ChatHistory();
        }
        botReply.setData(config.textHandler().process(chatHistory, chatUpdate));
        config.chatHistoryRepository().save(chatId, chatHistory);
        return botReply;
    }
}
