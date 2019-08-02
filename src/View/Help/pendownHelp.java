package View.Help;
import View.UserHistory;

public class pendownHelp extends Help {

    public static final String pendownHelp = "Syntax: PENDOWN? or PENDOWNP\n"
            + "returns 1 if the turtle's pen is down, 0 if it is up";

    public pendownHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = pendownHelp;
    }

}