package View.Help;
import View.UserHistory;

public class notHelp extends Help {

    public static final String notHelp = "Syntax: NOT test\n"
            + "returns 1 if test is 0 and 0 if test is non-zero";

    public notHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = notHelp;
    }

}