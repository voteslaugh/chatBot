package bot.functions;

public class WaitCommand extends Function {

    @Override
    public Status doFunction(String messageText) {
        switch (messageText) {
            case "/sleep":
                return Status.SLEEPING;
            case "/bintest":
                return Status.BINARY_TESTING;
            case "/easytest":
            default:
                return Status.WAITING_COMMAND;
        }
    }

    @Override
    public FunctionReply getFunctionReply() {
        FunctionReply functionReply = new FunctionReply();
        functionReply.setText("I'm waiting command...");
        return functionReply;
    }

    @Override
    public Status getStatus() {
        return Status.WAITING_COMMAND;
    }
}
