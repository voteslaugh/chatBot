package components;

import bot.functions.Function;

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
