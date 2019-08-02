package View.Help;
import View.UserHistory;

public class logHelp extends Help {

    public static final String logHelp = "Syntax: LOG expr\n"
            + "returns natural log of expr";

    public logHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = logHelp;
    }

}
