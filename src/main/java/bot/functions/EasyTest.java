package bot.functions;

import components.Task;
import components.TaskGenerator;

public class EasyTest extends  Function {
    private final Status status = Status.EASY_TESTING;
    private FunctionReply functionReply = new FunctionReply();
    private TaskGenerator taskGenerator;
    private Task task;

    public EasyTest(TaskGenerator taskGenerator) {
        this.taskGenerator = taskGenerator;
        this.task = taskGenerator.getSimpleTask();
    }

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
