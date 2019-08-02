package View.Help;
import View.UserHistory;

public class powHelp extends Help {

    public static final String powHelp = "Syntax: POW base exponent\n"
            + "returns base raised to the power of exponent";

    public powHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = powHelp;
    }

}
