package bot.functions.components;

import bot.functions.services.Function;
import java.util.Arrays;

public class Command {
    private String name;
    private String [] namesAfterCommands;
    private Function function;

    public Command(String name, Function function, String... namesAfterCommands) {
        this.name = name;
        this.function = function;
        this.namesAfterCommands = namesAfterCommands;
    }

    public boolean canUseWhit(String name) {
        return Arrays.asList(this.namesAfterCommands).contains(name);
    }

    public String getName() {
        return name;
    }

    public Function getFunction() {
        return function;
    }
}
