package View.Help;
import View.UserHistory;

public class orHelp extends Help {

    public static final String orHelp = "Syntax: OR test1 test2\n"
            + "returns 1 if test1 or test2 are non-zero, otherwise 0";

    public orHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = orHelp;
    }

}