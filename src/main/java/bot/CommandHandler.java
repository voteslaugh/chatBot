package bot;

import bot.functions.Command;
import java.util.HashMap;
import java.util.List;

public class CommandHandler {
    private HashMap<String, Command> navigation = new HashMap<>();

    public void addCommand(Command command) {
        String name = command.getName();
        navigation.put(name, command);
    }

    public Command getCommand(String nameCommand) {
        return navigation.get(nameCommand);
    }

    public List<Command> getListCommand() {
        return navigation.values().stream().toList();
    }
}
