package bot;

import bot.functions.Command;
import java.util.HashMap;
import java.util.List;

public class CommandHandler {
    private HashMap<String, Command> navigation = new HashMap<>();

    public void addCommand(Command command) {
        String name = command.getName();
        if (name.charAt(0) != '/') {
            name = '/' + name;
        }
        name = name.replaceAll("\s", "_");
        navigation.put(name, command);
    }

    public Command getCommand(String nameCommand) {
        return navigation.get(nameCommand);
    }

    public List<Command> getListCommand() {
        return navigation.values().stream().toList();
    }
}
