package bot;

import bot.api.ChatUpdate;
import bot.functions.Button;
import bot.functions.Data;
import bot.functions.Command;
import bot.functions.FunctionReply;
import java.util.ArrayList;
import java.util.List;

public class TextHandler {
    private CommandHandler commandHandler;

    public TextHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public Data process(ChatHistory chatHistory, ChatUpdate chatUpdate) {
        Data data = new Data();

        String text = chatUpdate.getMessage().getText();
        Command command = commandHandler.getCommand(text);

        if (command == null) {
            String nameLastCommand = chatHistory.getNameCommand();
            Command lastCommand = commandHandler.getCommand(nameLastCommand);

            if (lastCommand == null) {
                data.setText("Жду команду...");
            } else {
                FunctionReply functionReply = lastCommand.getFunction().doFunction(chatHistory, chatUpdate);
                if (functionReply.isFinished())
                    chatHistory.setNameCommand(null);
                data = functionReply.getData();
            }
        } else {
            FunctionReply functionReply = command.getFunction().preprocess(chatHistory, chatUpdate);

            if (!functionReply.isFinished()) {
                chatHistory.setNameCommand(text);
            }
            data = functionReply.getData();
        }
        data.setKeyboard(getKeyboard());
        return data;
    }
    private List<List<Button>> getKeyboard() {
        List<List<Button>> rowsKeyboard = new ArrayList<>();
        List<Button> rowKeyboard = new ArrayList<>();

        for (Command command : commandHandler.getListCommand()) {
            if (command.isAddToKeyBoard()) {
                rowKeyboard.add(new Button(command.getName()));
            }
        }
        rowsKeyboard.add(rowKeyboard);
        return rowsKeyboard;
    }
    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}
