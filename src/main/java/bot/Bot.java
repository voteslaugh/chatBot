package bot;

import bot.api.BotReply;
import bot.api.ChatUpdate;
import bot.functions.Command;
import java.util.List;

public interface Bot {
    BotReply reply(ChatUpdate chatUpdate);
    List<Command> getMenuCommands();
}