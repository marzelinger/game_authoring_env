package View.Help;
import View.UserHistory;

public class atanHelp extends Help {

    public static final String atanHelp = "Syntax: ATAN degrees\n"
            + "returns arctangent of degrees";

    public atanHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = atanHelp;
    }

}

