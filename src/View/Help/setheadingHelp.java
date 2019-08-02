package View.Help;

import View.UserHistory;

public class setheadingHelp extends Help {

    public static final String setheadingHelp = "Syntax: SETHEADING degrees\n"
            + "turns turtle to an absolute heading\n returns number of degrees moved";

    public setheadingHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = setheadingHelp;
    }

}