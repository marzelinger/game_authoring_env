package View.Help;
import View.UserHistory;

public class notequalHelp extends Help {

    public static final String notequalHelp = "Syntax: NOTEQUAL? or NOTEQUALP expr1 expr2\n"
            + "returns 1 if the value of expr1 and the value of expr2 are not equal, otherwise 0";

    public notequalHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = notequalHelp;
    }

}