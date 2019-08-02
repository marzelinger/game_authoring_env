package View.Help;
import View.UserHistory;

public class equalHelp extends Help {

    public static final String equalHelp = "Syntax: EQUAL? or EQUALP expr1 expr2\n"
            + "returns 1 if the value of expr1 and the value of expr2 are equal, otherwise 0";

    public equalHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = equalHelp;
    }

}