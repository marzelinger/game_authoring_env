package View.Help;

import View.UserHistory;

public class minusHelp extends Help {

    public static final String minusHelp = "Syntax: MINUS expr\n"
            + "returns negative of the values of expr";

    public minusHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = minusHelp;
    }

}
