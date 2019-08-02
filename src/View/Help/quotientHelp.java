package View.Help;

import View.UserHistory;

public class quotientHelp extends Help{

    public static final String quotientHelp = "Syntax: QUOTIENT expr1 expr2\n"
            + "returns quotient of the values expr1 and expr2";

    public quotientHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = quotientHelp;
    }
}
