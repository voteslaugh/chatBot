package services;

import bot.Bot;
import bot.api.BotReply;
import bot.api.ChatUpdate;
import components.KeyboardFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramChatBot extends TelegramLongPollingBot {
    private final String BOT_TOKEN;
    private final String BOT_NAME;
    private final Bot BOT;
    private final KeyboardFactory keyboardFactory;

    public TelegramChatBot(String BOT_NAME, String BOT_TOKEN, Bot BOT, KeyboardFactory keyboardFactory) {
        this.BOT_NAME = BOT_NAME;
        this.BOT_TOKEN = BOT_TOKEN;
        this.BOT = BOT;
        this.keyboardFactory = keyboardFactory;
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
        outMessage.setReplyMarkup(keyboardFactory.buildInLineKeyboard(botReply.getInLineKeyboard()));
        outMessage.setReplyMarkup(keyboardFactory.buildKeyboard(botReply.getKeyboard()));
        try {
            execute(outMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}