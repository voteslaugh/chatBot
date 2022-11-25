package bot;

import bot.functions.FunctionReply;
import bot.functions.Command;

public class TextHandler {
    private CommandHandler commandHandler;

    public TextHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public FunctionReply process(ChatHistory chatHistory, Message message) {
        FunctionReply functionReply = new FunctionReply();
        Command command;
        String text = message.getText();

        if (!text.equals("") && text.charAt(0) == '/') {
            command = commandHandler.getCommand(text);
            if (command == null) {
                functionReply.setText("Неизвестная команда");
            } else {
                chatHistory.setNameCommand(text);
                functionReply = command.getFunction().startFunction(chatHistory);
            }
        } else {
            String nameLastCommand = chatHistory.getNameCommand();
            Command lastCommand = commandHandler.getCommand(nameLastCommand);

            if (lastCommand == null) {
                functionReply.setText("Жду команду...");
            } else {
                functionReply = lastCommand.getFunction().doFunction(chatHistory, message);
            }
        }
        return functionReply;

//
//        boolean lastCommandIsDefault = Objects.equals(nameLastCommand, commandHandler.getDefaultNameCommand());
//        if (text.charAt(0) == '/') {
//            Command command = commandHandler.getCommand(text);
//            if (command == null) {
//                functionReply.setText("Неизвестная команда :(");
//            } else if (!(command.isCompatible(lastCommand))) {
//                functionReply.setText("Недоступная команда :(");
//            } else {
//                if (command.isChangeContext()) {
//                    chatHistory.setNameCommand(text);
//                    lastCommandIsDefault = Objects.equals(text, commandHandler.getDefaultNameCommand());
//                }
//                functionReply = command.getFunction().doFunction(chatHistory, null);
//            }
//        } else {
//            functionReply = lastCommand.getFunction().doFunction(chatHistory, text);
//        }
//        if (!lastCommandIsDefault) {
//            List<List<Button>> rowsInline = functionReply.getKeyboard();
//            if (rowsInline == null) {
//                rowsInline = new ArrayList<>();
//                List<Button> rowInline = new ArrayList<>();
//                rowInline.add(new Button("/help"));
//                rowsInline.add(ro wInline);
//                functionReply.setKeyboard(rowsInline);
//            } else {
//                rowsInline.get(0).add(new Button("/help"));
//            }
//        }
//        return functionReply;

        // Могут возникнуть ошибки, если:
        //      1) lastCommand == null
        //      2) getFunction() -> null
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}
