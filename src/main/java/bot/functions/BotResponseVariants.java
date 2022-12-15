package bot.functions;

public class BotResponseVariants extends Randomizer{
    String[] rightAnswerReplies;
    String[] wrongAnswerReplies;

    String[] badFormReplies;

    public BotResponseVariants(String[] rightAnswerReplies, String[] wrongAnswerReplies, String[] badFormReplies) {
        this.rightAnswerReplies = rightAnswerReplies;
        this.wrongAnswerReplies = wrongAnswerReplies;
        this.badFormReplies = badFormReplies;
    }

    public String getRandomReply(BotResponseCase responseCase)
    {
        return switch (responseCase){
            case RIGHT_ANSWER -> rightAnswerReplies[(int)getRandomIndex(rightAnswerReplies.length)];
            case WRONG_ANSWER -> wrongAnswerReplies[(int)getRandomIndex(wrongAnswerReplies.length)];
            case BAD_FORM -> badFormReplies[(int)getRandomIndex(badFormReplies.length)];
        };
    }


}
