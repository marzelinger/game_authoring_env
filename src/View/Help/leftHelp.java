package View.Help;

import View.UserHistory;

public class leftHelp extends Help {

    public static final String leftHelp = "Syntax: LEFT or LT degrees \n turns turtle counterclockwise by degrees angle\n" +
            " returns the value of degrees";

    public leftHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = leftHelp;
    }

}