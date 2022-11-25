package bot.functions;

public class Command {
    private String name;
    private String description;
    private Function function;
    private boolean addToKeyBoard = false;

    public Command(String name, String description, Function function) {
        this.function = function;
        this.name = name;
        this.description = description;
    }
    public Command(String name, String description, Function function, boolean addToKeyBoard) {
        this.function = function;
        this.name = name;
        this.description = description;
        this.addToKeyBoard = addToKeyBoard;
    }

    public Function getFunction() {
        return function;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAddToKeyBoard() {
        return addToKeyBoard;
    }
}
