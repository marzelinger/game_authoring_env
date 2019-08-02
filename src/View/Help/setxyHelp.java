package View.Help;

import View.UserHistory;

public class setxyHelp extends Help {

    public static final String setxyHelp = "Syntax: SETXY x y\n"
            + "moves turtle to an absolute screen position, where (0, 0) is the center of the screen\n" +
            "returns the distance turtle moved";

    public setxyHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = setxyHelp;
    }

}