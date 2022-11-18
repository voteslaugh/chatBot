package bot.components;

import bot.functions.api.FunctionReply;
import bot.functions.components.Command;
import bot.models.ChatHistory;

import java.util.Objects;

public class TextHandler {
    private CommandHandler commandHandler;

    public TextHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public FunctionReply process(ChatHistory chatHistory, String text) {
        String nameLastCommand = chatHistory.getNameCommand();
        Command lastCommand = commandHandler.getCommand(nameLastCommand);
        if (text.charAt(0) == '/') {
            Command command = commandHandler.getCommand(text);
            FunctionReply functionReply = new FunctionReply();
            if (command == null) {
                functionReply.setText("Неизвестная команда :(");
            } else if (!(command.isCompatible(lastCommand))) {
                functionReply.setText("Недоступная команда :(");
            } else {
                if (command.isChangeContext()) {
                    chatHistory.setNameCommand(text);
                }
                return command.getFunction().doFunction(chatHistory, null);
            }
            if (!Objects.equals(chatHistory.getNameCommand(), commandHandler.getDefaultNameCommand())) {
                return functionReply;
            }
        }
        return lastCommand.getFunction().doFunction(chatHistory, text);

        // Могут возникнуть ошибки, если:
        //      1) lastCommand == null
        //      2) getFunction() -> null
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}
