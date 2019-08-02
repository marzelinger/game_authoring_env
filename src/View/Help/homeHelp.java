package View.Help;

import View.UserHistory;

public class homeHelp extends Help {

    public static final String homeHelp = "Syntax: HOME \n moves turtle to the center of the screen (0 0)\n" +
            "returns the distance turtle moved\n";

    public homeHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = homeHelp;
    }

}