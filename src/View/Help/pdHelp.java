package View.Help;

import View.UserHistory;

public class pdHelp extends Help {

    public static final String pdHelp = "Syntax: PENDOWN or PD degrees\n"
            + "puts pen down such that when the turtle moves, it leaves a trail\n" +
            "returns 1";

    public pdHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = pdHelp;
    }

}