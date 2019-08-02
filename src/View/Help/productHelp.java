package View.Help;

import View.UserHistory;

public class productHelp extends Help{

    public static final String productHelp = "Syntax: PRODUCT expr1 expr2\n"
            + "returns product of the values expr1 and expr2";

    public productHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = productHelp;
    }
}
