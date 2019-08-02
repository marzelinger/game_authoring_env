package View.Help;

import View.UserHistory;

public class differenceHelp extends Help{

    public static final String differenceHelp = "Syntax: DIFFERENCE expr1 expr2\n"
            + "returns difference of the values expr1 and expr2";

    public differenceHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = differenceHelp;
    }

}

