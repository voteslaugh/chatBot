package bot.functions;

import bot.ChatUpdate;
import bot.configs.FunctionConfig;

public abstract class Function {
    protected FunctionReply functionReply = new FunctionReply();
    protected FunctionConfig functionConfig;
    public abstract Function doFunction(ChatUpdate chatUpdate);
    public abstract FunctionReply getFunctionReply();

    public Function(FunctionConfig functionConfig) {
        this.functionConfig = functionConfig;
    }
}
