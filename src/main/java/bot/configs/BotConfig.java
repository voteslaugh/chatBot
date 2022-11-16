package bot.configs;

import bot.functions.services.CommandHandler;
import bot.components.DataBase;

public record BotConfig(DataBase dataBase, CommandHandler commandHandler) {
}
