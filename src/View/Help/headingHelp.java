package View.Help;
import View.UserHistory;

public class headingHelp extends Help {

    public static final String headingHelp = "Syntax: HEADING\n"
            + "returns the turtle's heading in degrees.";

    public headingHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = headingHelp;
    }

}