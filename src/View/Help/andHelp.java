package View.Help;
import View.UserHistory;

public class andHelp extends Help {

    public static final String andHelp = "Syntax: AND test1 test2\n"
            + "returns 1 if test1 and test2 are non-zero, otherwise 0";

    public andHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = andHelp;
    }

}