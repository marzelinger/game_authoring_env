package View.Help;

import View.UserHistory;

public class randomHelp extends Help {

    public static final String randomHelp = "Syntax: RANDOM max\n"
            + "returns random non-negative number strictly less than max";

    public randomHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = randomHelp;
    }

}
