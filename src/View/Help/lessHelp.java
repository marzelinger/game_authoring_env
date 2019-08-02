package View.Help;
import View.UserHistory;

public class lessHelp extends Help {

    public static final String lessHelp = "Syntax: LESS? or LESSP expr1 expr2\n"
            + "returns 1 if the value of expr1 is strictly less than the value of expr2, otherwise 0";

    public lessHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = lessHelp;
    }

}

