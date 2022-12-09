package bot.functions;

import bot.ChatHistory;
import bot.Stat;
import bot.StatRepository;
import bot.api.ChatUpdate;
import java.util.List;

public class Rating implements Function {
    StatRepository statRepository;

    public Rating(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    @Override
    public FunctionReply doFunction(ChatHistory chatHistory, ChatUpdate chatUpdate) {
        List<Stat> allStat = statRepository.getAllStat();
        Data data = new Data();

        if (allStat.isEmpty()) {
            data.setText("Рейтинг еще не сфоримировался\uD83D\uDE15");
        } else {
//            allStat.sort(Comparator.comparingInt(Stat::getCount));

// отсортировать статистику и красиво вывести
            StringBuilder rating = new StringBuilder();
            String a = "";

            for (Stat stat : allStat) {
                int n = 0;
                rating.append(String.format("место %d - ", n)).append(stat.getCount()).append('\n');
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
