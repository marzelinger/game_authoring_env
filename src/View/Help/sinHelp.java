package View.Help;
        import View.UserHistory;

public class sinHelp extends Help {

    public static final String sinHelp = "Syntax: SIN degrees\n"
            + "returns sine of degrees";

    public sinHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = sinHelp;
    }

}
