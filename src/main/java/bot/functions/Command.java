package bot.functions;

public class Command {
    private String name;
    private String description;
    private Function function;
    private boolean addToKeyBoard = false;
    private boolean addToMenu;

    public Command(String name, String description, Function function, boolean addToMenu) {
        this.function = function;
        this.name = name;
        this.description = description;
        this.addToMenu = addToMenu;
    }
    public Command(String name, String description, Function function, boolean addToMenu, boolean addToKeyBoard) {
        this.function = function;
        this.name = name;
        this.description = description;
        this.addToKeyBoard = addToKeyBoard;
        this.addToMenu = addToMenu;
    }

    public boolean isAddToMenu() {
        return addToMenu;
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
