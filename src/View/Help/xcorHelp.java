package View.Help;
import View.UserHistory;

public class xcorHelp extends Help {

    public static final String xcorHelp = "Syntax: XCOR\n"
            + "returns the turtle's X coordinate from the center of the screen.";

    public xcorHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = xcorHelp;
    }

}