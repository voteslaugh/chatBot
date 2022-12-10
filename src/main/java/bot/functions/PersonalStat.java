package bot.functions;

import bot.ChatHistory;
import bot.Stat;
import bot.StatRepository;
import bot.api.ChatUpdate;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonalStat implements Function {
    StatRepository [] statRepositories;
    String format;

    public PersonalStat(String format, StatRepository ... statRepositories) {
        this.statRepositories = statRepositories;
        this.format = format;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, ChatUpdate chatUpdate) {
        FunctionReply functionReply = new FunctionReply();

        Data data = new Data();
        String chatId = chatUpdate.getChatId();
        Stat stat0 = statRepositories[0].getStat(chatId);
        Stat stat1 = statRepositories[1].getStat(chatId);

        data.setText(String.format(format, stat0 == null ? 0 : stat0.getCount(),
                stat1 == null ? 0 : stat1.getCount()));
        functionReply.finish();
        functionReply.setData(data);
        return functionReply;
    }

    @Override
    public FunctionReply preprocess(ChatHistory chatHistory, ChatUpdate chatUpdate) {
        return  doFunction(null, chatUpdate);
    }
}
