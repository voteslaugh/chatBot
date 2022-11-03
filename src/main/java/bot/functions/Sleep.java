package bot.functions;

public class Sleep extends Function {

    @Override
    public Status doFunction(String messageText) {
        return switch (messageText) {
            case "/start" -> Status.WAITING_COMMAND;
            default -> Status.SLEEPING;
        };
    }

    @Override
    public FunctionReply getFunctionReply() {
        FunctionReply functionReply = new FunctionReply();
        functionReply.setText("(￣o￣) zzZZzzZZ");
        return functionReply;
    }

    @Override
    public Status getStatus() {
        return Status.SLEEPING;
    }
}
