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

    public Command(Function function, FunctionGroup functionGroup, String... names) {
        this.function = function;
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
}
