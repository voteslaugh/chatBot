package bot;

public abstract class Program {
    protected final MathBot mathBot = new MathBot();
    protected abstract ChatUpdate getChatUpdate(); // Перевод Update среды в ChatUpdate, который понятен боту
    public abstract void run();
}
