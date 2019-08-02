package View.Help;

import View.UserHistory;

public class showturtleHelp extends Help {

    public static final String stHelp = "Syntax: SHOWTURTLE or ST\n"
            + "makes turtle visible\n" +
            "returns 1";

    public showturtleHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = stHelp;
    }

}