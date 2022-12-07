package bot;

import bot.api.BotReply;
import bot.api.ChatUpdate;
import bot.functions.Command;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

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
        List<BotCommand> commands = new ArrayList<>();

        for (Command command : BOT.getMenuCommands()) {
            commands.add(new BotCommand(command.getName(), command.getDescription()));
        }

        try {
            this.execute(new SetMyCommands(commands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
        String text = "";
        String userId = "";
        String chatId = "";
        String callBack = null;

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            userId = callbackQuery.getFrom().getId().toString();
            chatId = callbackQuery.getMessage().getChatId().toString();
            callBack = callbackQuery.getData();

        } else if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            userId = message.getFrom().getId().toString();
            chatId = message.getChatId().toString();
            text = message.getText();
        }
        ChatUpdate chatUpdate = new ChatUpdate(userId, chatId);
        chatUpdate.setCallback(callBack);
        chatUpdate.setText(text);
        sendMessage(BOT.reply(chatUpdate));
    }

    public void sendMessage(BotReply botReply) {
        SendMessage outMessage = new SendMessage();
        outMessage.setChatId(botReply.getChatId());
        outMessage.setText(botReply.getText());
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardFactory.buildInLineKeyboard(botReply.getInLineKeyboard());

        if (inlineKeyboardMarkup == null) {
            ReplyKeyboard replyKeyboard = keyboardFactory.buildKeyboard(botReply.getKeyboard());
            if (replyKeyboard != null) {
                outMessage.setReplyMarkup(replyKeyboard);
            }
        } else {
            outMessage.setReplyMarkup(inlineKeyboardMarkup);
        }
        try {
            execute(outMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}