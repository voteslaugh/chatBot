package bot;

import bot.functions.Data;
import bot.functions.Command;
import bot.functions.FunctionReply;

public class TextHandler {
    private CommandHandler commandHandler;

    public TextHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public Data process(ChatHistory chatHistory, Message message) {
        Data data = new Data();

        String text = message.getText();
        Command command = commandHandler.getCommand(text);

        if (command == null) {
            String nameLastCommand = chatHistory.getNameCommand();
            Command lastCommand = commandHandler.getCommand(nameLastCommand);

            if (lastCommand == null) {
                data.setText("Жду команду...");
            } else {
                FunctionReply functionReply = lastCommand.getFunction().doFunction(chatHistory, message);
                if (functionReply.isFinished())
                    chatHistory.setNameCommand(null);
                data = functionReply.getData();
            }
        } else {
            FunctionReply functionReply = command.getFunction().preprocess(chatHistory);

            if (!functionReply.isFinished()) {
                chatHistory.setNameCommand(text);
            }
            data = functionReply.getData();
         }
        return data;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}
