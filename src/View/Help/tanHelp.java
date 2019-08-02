package View.Help;
import View.UserHistory;

public class tanHelp extends Help {

    public static final String tanHelp = "Syntax: TAN degrees\n"
            + "returns sine of degrees";

    public tanHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = tanHelp;
    }

}

