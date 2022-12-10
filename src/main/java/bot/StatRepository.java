package bot;

import java.util.HashMap;
import java.util.List;

public class StatRepository implements Repository {

    private HashMap<String, Stat> statistics = new HashMap<>();

    public Stat getStat(String chatId) {
        return statistics.get(chatId);
    }

    public List<Stat> getAllStat() {
        return statistics.values().stream().toList();
    }
    public void save(String chatId, Stat stat) {
        statistics.put(chatId, stat);
    }
}
