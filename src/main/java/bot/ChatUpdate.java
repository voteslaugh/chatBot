package bot;

public class ChatUpdate {
    private String userId;
    private String message;
    public ChatUpdate(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }
    public String getUserId() {
        return this.userId;
    }
    public String getMessage() {
        return this.message;
    }

}
