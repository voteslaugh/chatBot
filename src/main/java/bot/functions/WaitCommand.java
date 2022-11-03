package bot.functions;

public class WaitCommand extends Function {

    @Override
    public Status doFunction(String messageText) {
        return switch (messageText) {
            case "/sleep" -> Status.SLEEPING;
            case "/bintest" -> Status.BINARY_TESTING;
            case "/easytest" -> Status.WAITING_COMMAND;
            default -> Status.WAITING_COMMAND;
        };
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
