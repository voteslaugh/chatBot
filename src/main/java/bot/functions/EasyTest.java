package bot.functions;

public class EasyTest extends  Function {
    @Override
    public Status doFunction(String messageText) {
        return null;
    }

    @Override
    public FunctionReply getFunctionReply() {
        return null;
    }

    @Override
    public Status getStatus() {
        return Status.EASY_TESTING;
    }
}
