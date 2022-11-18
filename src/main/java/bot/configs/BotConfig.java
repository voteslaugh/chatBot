package bot.configs;

import bot.components.CommandHandler;
import bot.components.DataBase;
import bot.components.TextHandler;

public record BotConfig(DataBase dataBase, TextHandler textHandler) {
}
