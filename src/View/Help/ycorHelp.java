package View.Help;
import View.UserHistory;

public class ycorHelp extends Help {

    public static final String ycorHelp = "Syntax: YCOR\n"
            + "returns the turtle's Y coordinate from the center of the screen.";

    public ycorHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = ycorHelp;
    }

}