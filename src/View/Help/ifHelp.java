package View.Help;

import View.UserHistory;

public class ifHelp extends Help {

    public static final String ifHelp = "Syntax: IF expr [ command(s) ]\nif expr is not 0, runs the command(s) given in the list\n" +
    "returns the value of the final command executed (or 0 if no commands are executed)";

    public ifHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = ifHelp;
    }

}