package bot.components;

import bot.functions.api.FunctionReply;
import bot.functions.components.Command;
import bot.functions.models.Button;
import bot.models.ChatHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextHandler {
    private CommandHandler commandHandler;

    public TextHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public FunctionReply process(ChatHistory chatHistory, String text) {
        String nameLastCommand = chatHistory.getNameCommand();
        Command lastCommand = commandHandler.getCommand(nameLastCommand);
        FunctionReply functionReply = new FunctionReply();
        boolean lastCommandIsDefault = Objects.equals(nameLastCommand, commandHandler.getDefaultNameCommand());
        if (text.charAt(0) == '/') {
            Command command = commandHandler.getCommand(text);
            if (command == null) {
                functionReply.setText("Неизвестная команда :(");
            } else if (!(command.isCompatible(lastCommand))) {
                functionReply.setText("Недоступная команда :(");
            } else {
                if (command.isChangeContext()) {
                    chatHistory.setNameCommand(text);
                    lastCommandIsDefault = Objects.equals(text, commandHandler.getDefaultNameCommand());
                }
                functionReply = command.getFunction().doFunction(chatHistory, null);
            }
        } else {
            functionReply = lastCommand.getFunction().doFunction(chatHistory, text);
        }
        if (!lastCommandIsDefault) {
            List<List<Button>> rowsInline = functionReply.getKeyboard();
            if (rowsInline == null) {
                rowsInline = new ArrayList<>();
                List<Button> rowInline = new ArrayList<>();
                rowInline.add(new Button("/help"));
                rowsInline.add(rowInline);
                functionReply.setKeyboard(rowsInline);
            } else {
                rowsInline.get(0).add(new Button("/help"));
            }
        }
        return functionReply;

        // Могут возникнуть ошибки, если:
        //      1) lastCommand == null
        //      2) getFunction() -> null
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}
