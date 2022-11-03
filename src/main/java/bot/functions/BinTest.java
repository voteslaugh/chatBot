package bot.functions;

import components.Task;
import components.TaskGenerator;
import java.util.Objects;

public class BinTest extends Function {
    private Task task;
    private FunctionReply functionReply = new FunctionReply();
    private TaskGenerator taskGenerator;


    public BinTest(TaskGenerator taskGenerator) {
        this.taskGenerator = taskGenerator;
        task = taskGenerator.getAdditionalCode();
        functionReply.setText(task.getQuestion());
    }

    @Override
    public Status runFunction(String messageText) {
        return switch (messageText) {
            case "/stop" -> Status.WAITING_COMMAND;
            default -> {
                if (Objects.equals(messageText, task.getAnswer())) {
                    task = taskGenerator.getAdditionalCode();
                    functionReply.setText("Excellently!\n" + task.getQuestion());
                } else {
                    functionReply.setText("Wrong answer. Try again!\n" + task.getQuestion());
                }
                yield  Status.BINARY_TESTING;
            }
        };
    }

    @Override
    public FunctionReply getFunctionReply() {
        return functionReply;
    }

    @Override
    public Status getStatus() {
        return Status.BINARY_TESTING;
    }
}
