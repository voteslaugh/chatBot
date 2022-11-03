package components;

import bot.BotStatus;
import bot.functions.Function;
import components.Task;

public class ChatHistory {
    private Function lastFunction;

    public ChatHistory(Function lastFunction) {
        this.lastFunction = lastFunction;
    }

    public Function getLastFunction() {
        return lastFunction;
    }

    public void setLastFunction(Function lastFunction) {
        this.lastFunction = lastFunction;
    }
}
