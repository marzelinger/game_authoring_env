package View.Help;

import View.UserHistory;

public class backHelp extends Help {

    public static final String backHelp = "Syntax: BACK or BK pixels \n moves turtle backward in its current heading by pixels distance\n" +
    "returns the value of pixels";

    public backHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = backHelp;
    }

}