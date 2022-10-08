package bot;

public abstract class ChatBot {
    protected final MathBot mathBot = new MathBot();
    public abstract void run();
}
