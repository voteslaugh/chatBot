package bot.functions.services;

import bot.functions.components.Command;
import java.util.HashMap;

public class CommandHandler {
    private String defaultNameCommand;
    private HashMap<String, Command> navigation = new HashMap<>();

    private void updateNavigation(Command command) {
        for (String name : command.getNames()) {
            navigation.put(name, command);
        }
    }
    public CommandHandler(String defaultNameCommand) {
        this.defaultNameCommand = defaultNameCommand;
    }

    public String getDefaultNameCommand() {
        return defaultNameCommand;
    }
    public void addCommand(Command command) {
        updateNavigation(command);
    }
    public Command getCommand(String nameCommand) {
        return navigation.get(nameCommand);
    }
}
