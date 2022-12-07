package bot;

import bot.functions.Button;
import bot.functions.InLineButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.ArrayList;
import java.util.List;

public class KeyboardFactory {
    public InlineKeyboardMarkup buildInLineKeyboard(List<List<InLineButton>> buttons) {
        if (buttons == null) return null;
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        for (List<InLineButton> buttonLines: buttons){
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            for(InLineButton button: buttonLines) {
                InlineKeyboardButton telegramButton = new InlineKeyboardButton();
                telegramButton.setText(button.name());
                telegramButton.setCallbackData(button.callback());
                rowInline.add(telegramButton);
            }
            rowsInline.add(rowInline);
        }
        keyboard.setKeyboard(rowsInline);
        return keyboard;
    }
    public ReplyKeyboardMarkup buildKeyboard(List<List<Button>> buttons) {
        if (buttons == null) return null;
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        List<KeyboardRow> rowsInline = new ArrayList<>();
        for (List<Button> buttonLines: buttons){
            KeyboardRow rowInline = new KeyboardRow();
            for(Button button: buttonLines) {
                KeyboardButton telegramButton = new KeyboardButton();
                telegramButton.setText(button.name());
                rowInline.add(telegramButton);
            }
            rowsInline.add(rowInline);
        }
        keyboard.setKeyboard(rowsInline);
        return keyboard;
    }
}
