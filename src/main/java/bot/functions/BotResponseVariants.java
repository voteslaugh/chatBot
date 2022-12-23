package bot.functions;
import static bot.functions.Randomizer.getRandomCollectionElement;

public class BotResponseVariants {
    String[] rightAnswerReplies;
    String[] wrongAnswerReplies;
    String[] badFormReplies;

    public BotResponseVariants(String[] rightAnswerReplies, String[] wrongAnswerReplies, String[] badFormReplies) {
        this.rightAnswerReplies = rightAnswerReplies;
        this.wrongAnswerReplies = wrongAnswerReplies;
        this.badFormReplies = badFormReplies;
    }

    public Object getRandomReply(BotResponseCase responseCase)
    {
        return switch (responseCase){
            case RIGHT_ANSWER -> getRandomCollectionElement(rightAnswerReplies);
            case WRONG_ANSWER -> getRandomCollectionElement(wrongAnswerReplies);
            case BAD_FORM -> getRandomCollectionElement(badFormReplies);
        };
    }

}
