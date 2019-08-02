package View.Help;

import View.UserHistory;

public class sumHelp extends Help{

    public static final String sumHelp = "Syntax: SUM expr1 expr2\n"
            + "returns sum of the values expr1 and expr2";

    public sumHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = sumHelp;
    }
}
