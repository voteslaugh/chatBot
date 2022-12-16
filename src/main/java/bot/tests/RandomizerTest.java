package bot.tests;

import bot.functions.Randomizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class RandomizerTest {
    Randomizer randomizer;

    @BeforeEach
    void prepareData() {
        randomizer = new Randomizer();
    }

    @Test
    void getLongInRangeTest() {
        int min = -1000, max = 1000;
        long randomDigit = randomizer.getLongInRange(min, max);
        Assertions.assertTrue(min <= randomDigit && randomDigit <= max);
    }

    @Test
    void getRandomIndexTest() {
        Short[] someList = new Short[] {0, 1};
        long randomIndex = randomizer.getRandomIndex(someList.length);
        Assertions.assertTrue(Arrays.asList(someList).contains(someList[(int) randomIndex]));
    }
}