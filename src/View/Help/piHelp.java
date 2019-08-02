package View.Help;
import View.UserHistory;

public class piHelp extends Help {

    public static final String piHelp = "Syntax: PI\n"
            + "Reports the number PI";

    public piHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = piHelp;
    }

}
