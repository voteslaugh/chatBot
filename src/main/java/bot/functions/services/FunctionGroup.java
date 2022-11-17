package bot.functions.services;

import java.util.Arrays;

public class FunctionGroup {
    private Function [] group;

    public FunctionGroup(Function... functions) {
        this.group = functions;
    }

    public boolean containsFunction(Function function) {
        return Arrays.asList(this.group).contains(function);
    }
}
