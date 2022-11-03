package bot.functions;

public abstract class Function {
    public abstract Status doFunction(String messageText);
    public abstract FunctionReply getFunctionReply();
    public abstract Status getStatus();
}
