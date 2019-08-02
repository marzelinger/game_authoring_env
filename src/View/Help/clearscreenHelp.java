package View.Help;

import View.UserHistory;

public class clearscreenHelp extends Help {

    public static final String clearscreenHelp = "Syntax: CLEARSCREEN or CS \n erases turtle's trails and sends it to the home position\n" +
            "returns the distance turtle moved\n";

    public clearscreenHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = clearscreenHelp;
    }

}