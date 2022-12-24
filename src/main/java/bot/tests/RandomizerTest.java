package bot.tests;

import bot.functions.Randomizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static bot.functions.Randomizer.getLongInRange;
import static bot.functions.Randomizer.getRandomCollectionElement;

class RandomizerTest {
    Randomizer randomizer;

    @Test
    void getLongInRangeTest() {
        int min = -1000, max = 1000;
        long randomDigit = getLongInRange(min, max);
        Assertions.assertTrue(min <= randomDigit && randomDigit <= max);
    }

    @Test
    void getRandomIndexTest() {
        Short[] someList = new Short[] {0, 1};
        Short randomElement = (Short) getRandomCollectionElement(someList);
        Assertions.assertTrue(Arrays.asList(someList).contains(randomElement));
    }
}