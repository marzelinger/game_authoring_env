package View.Help;
import View.UserHistory;

public class greaterHelp extends Help {

    public static final String greaterHelp = "Syntax: GREATER? or GREATERP expr1 expr2\n"
            + "returns 1 if the value of expr1 is strictly greater than the value of expr2, otherwise 0";

    public greaterHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = greaterHelp;
    }

}

