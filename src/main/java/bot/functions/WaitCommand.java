package bot.functions;

import bot.ChatUpdate;
import bot.configs.FunctionConfig;

public class WaitCommand extends Function {
    public WaitCommand(FunctionConfig functionConfig) {
        super(functionConfig);
    }

    @Override
    public Function doFunction(ChatUpdate chatUpdate) {
        switch (chatUpdate.getText()) {
            case "/sleep":
                return new Sleep(functionConfig);
            case "/bintest":
                return new BinTest(functionConfig);
            case "/easytest":
            default:
                return this;
        }
    }

    @Override
    public FunctionReply getFunctionReply() {
        functionReply.setText("I'm waiting command...");
        return functionReply;
    }
}
