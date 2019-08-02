package View.Help;

import View.UserHistory;

public class hideturtleHelp extends Help {

    public static final String htHelp = "Syntax: HIDETURTLE or HT\n"
            + "makes turtle invisible\n" +
            "returns 0";

    public hideturtleHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = htHelp;
    }

}