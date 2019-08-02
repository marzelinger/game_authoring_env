package View.Help;

import View.UserHistory;

/**
 * @author Ryan Piersma
 * Help subclass documenting how to use the make command in SLogo
 */
public class makeHelp extends Help {

    public static final String makeHelp = "Syntax: MAKE variable expr \nassigns the value of expr to variable, creating the variable if necessary\n" +
            "returns expr\n";

    public makeHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = makeHelp;
    }

}