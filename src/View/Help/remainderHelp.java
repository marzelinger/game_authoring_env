package View.Help;

import View.UserHistory;

public class remainderHelp extends Help{

    public static final String remainderHelp = "Syntax: REMAINDER expr1 expr2\n"
            + "returns remainder on dividing the values of expr1 and expr2";

    public remainderHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = remainderHelp;
    }
}
