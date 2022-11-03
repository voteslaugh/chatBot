package bot.functions;

public class Sleep extends Function {

    @Override
    public Status doFunction(String messageText) {
        switch (messageText) {
            case "/start":
                return Status.WAITING_COMMAND;
            default:
                return Status.SLEEPING;
        }
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
