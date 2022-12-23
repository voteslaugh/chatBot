package bot.functions;

public class Randomizer {
    public static long getLongInRange(long min, long max){

        return ((long) (Math.random() * ((max - min) + 1)))+min;
    }

    public static Object getRandomCollectionElement(Object[] stringCollection) {
        return stringCollection[(int) getLongInRange(0, stringCollection.length - 1)];
    }

}
