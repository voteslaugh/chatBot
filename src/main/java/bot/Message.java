package bot;

public class Message { // поправить название
    private String text;
    private String callback;
    private boolean hasCallback = false;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
        this.hasCallback = callback != null;
    }

    public boolean hasCallback() {
        return hasCallback;
    }
}
