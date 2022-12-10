package bot.functions;

import bot.ChatHistory;
import bot.Stat;
import bot.StatRepository;
import bot.User;
import bot.api.ChatUpdate;
import java.util.Comparator;
import java.util.List;

public class Rating implements Function {
    StatRepository statRepository;
    String header;

    public Rating(String header, StatRepository statRepository) {
        this.header = header;
        this.statRepository = statRepository;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, ChatUpdate chatUpdate) {
        List<Stat> allStat = statRepository.getAllStat();
        Data data = new Data();

        if (allStat.isEmpty()) {
            data.setText("Рейтинг еще не сфоримировался\uD83D\uDE15");
        } else {
            allStat = allStat.stream().sorted(Comparator.comparingInt(Stat::getCount)).toList();
// отсортировать статистику и красиво вывести
            StringBuilder rating = new StringBuilder();
            rating.append(header).append('\n');

            int n = 1;
            for (Stat stat : allStat) {
                User user = stat.getUser();
                String n1 = user.getFirstName();
                String n2 = user.getLastName();
                rating.append(String.format("%d) %s %s %d\n", n, n1 , n2, stat.getCount()));
                n += 1;
            }
            data.setText(rating.toString());
        }

        FunctionReply functionReply = new FunctionReply();
        functionReply.setData(data);
        functionReply.finish();
        return functionReply;
    }

    @Override
    public FunctionReply preprocess(ChatHistory chatHistory, ChatUpdate chatUpdate) {
        return doFunction(chatHistory, chatUpdate);
    }
}
