package View.Help;

import View.UserHistory;

public class rightHelp extends Help {

    public static final String rightHelp = "Syntax: RIGHT or RT degrees \n turns turtle clockwise by degrees angle\n" +
    " returns the value of degrees";

    public rightHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = rightHelp;
    }

}