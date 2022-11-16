package bot.functions.services;

import bot.functions.components.Command;
import java.util.HashMap;

public class CommandHandler {
    private String nameDefaultCommand;
    private HashMap<String, Command> navigation = new HashMap<>();
    public CommandHandler(Command defaultCommand) {
        this.nameDefaultCommand = defaultCommand.getName();
        this.navigation.put(nameDefaultCommand, defaultCommand);
    }

    public void addCommand(Command command) {
        navigation.put(command.getName(), command);
    }
    public String getNameDefaultCommand() {
        return nameDefaultCommand;
    }

    public Command getCommand(String nameCommand) {
        return navigation.get(nameCommand);
    }
}
