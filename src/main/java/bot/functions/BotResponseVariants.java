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
            case RIGHT_ANSWER -> rightAnswerReplies[(int)getLongInRange(0, rightAnswerReplies.length)];
            case WRONG_ANSWER -> wrongAnswerReplies[(int)getLongInRange(0, wrongAnswerReplies.length)];
            case BAD_FORM -> badFormReplies[(int)getLongInRange(0, badFormReplies.length)];
        };
    }


}
