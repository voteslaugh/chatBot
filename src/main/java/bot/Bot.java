package bot;

import bot.api.BotReply;
import bot.api.ChatUpdate;

public interface Bot {
    BotReply reply(ChatUpdate chatUpdate);
}