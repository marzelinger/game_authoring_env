package View.Help;
import View.UserHistory;

public class showingHelp extends Help {

    public static final String showingHelp = "Syntax: SHOWING? or SHOWINGP\n"
            + "returns 1 if the turtle is showing, 0 if it is hiding.";

    public showingHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = showingHelp;
    }

}