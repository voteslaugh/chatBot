package bot.configs;

import bot.DataBase;
import bot.TextHandler;

public record BotConfig(DataBase dataBase, TextHandler textHandler) {
}
