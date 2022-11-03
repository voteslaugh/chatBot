package bot.functions;

import bot.ChatUpdate;
import bot.configs.FunctionConfig;
import components.Task;

import java.util.Objects;

public class BinTest extends Function {

    private Task task = functionConfig.getTaskGenerator().getAdditionalCode();


    public BinTest(FunctionConfig functionConfig) {
        super(functionConfig);
        functionReply.setText(task.getQuestion());
    }

    @Override
    public Function doFunction(ChatUpdate chatUpdate) {
        String text = chatUpdate.getText();
        switch (text) {
            case "/stop":
                return new WaitCommand(functionConfig);
            default:
                if(Objects.equals(text, task.getAnswer())) {
                    task = functionConfig.getTaskGenerator().getAdditionalCode();
                    functionReply.setText("Excellently!\n" + task.getQuestion());
                } else {
                    functionReply.setText("Wrong answer. Try again!\n" + task.getQuestion());
                }
                return this;
        }
    }

    @Override
    public FunctionReply getFunctionReply() {
        return functionReply;
    }
}
