package bot.functions;

import java.util.List;

public class Data {
    private String text;
    private List<List<InLineButton>> inLineKeyboard;
    private List<List<Button>> keyboard;

    public List<List<InLineButton>> getInLineKeyboard() {
        return inLineKeyboard;
    }

    public void setInLineKeyboard(List<List<InLineButton>> inLineKeyboard) {
        this.inLineKeyboard = inLineKeyboard;
    }

    public List<List<Button>> getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(List<List<Button>> keyboard) {
        this.keyboard = keyboard;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
