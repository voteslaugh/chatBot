package bot.functions;

import bot.ChatUpdate;
import bot.configs.FunctionConfig;

public class Sleep extends Function {
    public Sleep(FunctionConfig functionConfig) {
        super(functionConfig);
    }

    @Override
    public Function doFunction(ChatUpdate chatUpdate) {
        switch (chatUpdate.getText()) {
            case "/start":
                return new WaitCommand(functionConfig);
            default:
                return this;
        }
    }

    @Override
    public FunctionReply getFunctionReply() {
        functionReply.setText("(￣o￣) zzZZzzZZ");
        return functionReply;
    }
}
