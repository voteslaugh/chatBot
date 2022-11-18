package bot.components;

import bot.functions.components.Command;
import java.util.HashMap;

public class CommandHandler {
    private String defaultNameCommand;
    private HashMap<String, Command> navigation = new HashMap<>();

    private void updateNavigation(Command command) {
        for (String name : command.getNames()) {
            if (name.charAt(0) != '/') {
                name = '/' + name;
            }
            name = name.replaceAll("\s", "_");
            navigation.put(name, command);
        }
    }

    public String getDefaultNameCommand() {
        return defaultNameCommand;
    }
    public void addCommand(Command command) {
        if (this.defaultNameCommand == null) {
            this.defaultNameCommand = command.getNames()[0];
        }
        updateNavigation(command);
    }
    public Command getCommand(String nameCommand) {
        return navigation.get(nameCommand);
    }
}
