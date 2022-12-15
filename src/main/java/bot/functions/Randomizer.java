package bot.functions;

public class Randomizer {
    public long getLongInRange(long min, long max){
        return ((long) (Math.random() * ((max - min) + 1)))+min;
    }

    public long getRandomIndex(int size) {
        return getLongInRange(0, size - 1);
    }

}
