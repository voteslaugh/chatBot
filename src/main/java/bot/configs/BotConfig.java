package bot.configs;

import bot.ChatHistoryRepository;
import bot.TextHandler;

public record BotConfig(ChatHistoryRepository chatHistoryRepository, TextHandler textHandler) {
}
