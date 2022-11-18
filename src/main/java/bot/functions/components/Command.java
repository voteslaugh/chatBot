package bot.functions.components;

import bot.functions.services.Function;
import bot.functions.services.FunctionGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command {
    private String[] names;
    private FunctionGroup functionGroup;
    private Function function;

    boolean changeContext;

    public Command(Function function, boolean changeContext, FunctionGroup functionGroup, String... names) {
        this.function = function;
        this.changeContext = changeContext;
        this.functionGroup = functionGroup;
        this.names = names;

    }

    public boolean isCompatible(Command command) {
        return this.functionGroup.containsFunction(command.function);
    }

    public Function getFunction() {
        return function;
    }

    public String[] getNames() {
        return names;
    }

    public boolean isChangeContext() {
        return changeContext;
    }
}
