package View.Help;

import View.UserHistory;

public class forwardHelp extends Help {

    public static final String forwardHelp = "Syntax: FORWARD or FD pixels \n moves turtle forward in its current heading by pixels distance\n" +
    "returns the value of pixels";

    public forwardHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = forwardHelp;
    }

}