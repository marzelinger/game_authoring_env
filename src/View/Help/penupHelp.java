package View.Help;

import View.UserHistory;

public class penupHelp extends Help {

    public static final String puHelp = "Syntax: PENUP or PU degrees\n"
            + "puts pen up such that when the turtle moves, it does not leave a trail\n" +
            "returns 0";

    public penupHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = puHelp;
    }

}