package bot.functions;

public class BotResponseVariants extends Randomizer{

    String[] badFormReplies = new String[]{"\uD83D\uDE09Нужно вводить числа!",
            "\uD83E\uDDD0Ответ может быть только числом!",
            "\uD83D\uDE0CСейчас я жду числа!"};

    String[] wrongAnswerReplies = new String[]{"\uD83D\uDE1BНеверно. Попробуй снова!\n\n",
            "\uD83E\uDD2AНеверно. Попробуй ещё раз!\n\n",
            "\uD83D\uDE04Не-а. Заново!\n\n"};

    String[] rightAnswerReplies = new String[]{"\uD83E\uDD79Я тобой горжусь! Следующий пример:\n\n",
            "\uD83E\uDD73Отлично! Следующий пример:\n\n",
            "\uD83D\uDE0DПотрясающе! Следующией пример:\n\n"};

    public String getRandomReply(BotResponseCase responseCase)
    {
        return switch (responseCase){
            case RIGHT_ANSWER -> rightAnswerReplies[(int)getLongInRange(0, rightAnswerReplies.length)];
            case WRONG_ANSWER -> wrongAnswerReplies[(int)getLongInRange(0, wrongAnswerReplies.length)];
            case BAD_FORM -> badFormReplies[(int)getLongInRange(0, badFormReplies.length)];
        };
    }


}
