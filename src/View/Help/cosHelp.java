package View.Help;
import View.UserHistory;

public class cosHelp extends Help {

    public static final String cosHelp = "Syntax: COS degrees\n"
            + "returns cosine of degrees";

    public cosHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = cosHelp;
    }

}
