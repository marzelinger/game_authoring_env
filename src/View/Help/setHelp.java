package View.Help;

import View.UserHistory;

public class setHelp extends Help {

    public static final String setHelp = "Syntax: SET variable expr \nassigns the value of expr to variable, creating the variable if necessary\n" +
            "returns expr\n";

    public setHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = setHelp;
    }

}