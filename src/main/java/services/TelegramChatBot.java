package services;

import bot.Bot;
import bot.BotReply;
import bot.ChatUpdate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramChatBot extends TelegramLongPollingBot {
    private final String BOT_TOKEN;
    private final String BOT_NAME;
    private final Bot BOT;

    public TelegramChatBot(String BOT_NAME, String BOT_TOKEN, Bot BOT) {
        this.BOT_NAME = BOT_NAME;
        this.BOT_TOKEN = BOT_TOKEN;
        this.BOT = BOT;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String text = message.getText();
            String userId = message.getFrom().getId().toString();
            String chatId = message.getChatId().toString();
            ChatUpdate chatUpdate = new ChatUpdate(userId, chatId);
            chatUpdate.setText(text);
            sendMessage(BOT.reply(chatUpdate));
        }
    }

    public void sendMessage(BotReply botReply) {
        SendMessage outMessage = new SendMessage();
        outMessage.setChatId(botReply.getChatId());
        outMessage.setText(botReply.getText());
        try {
            execute(outMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}