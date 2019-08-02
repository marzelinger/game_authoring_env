package View.Help;

import View.UserHistory;

public class forHelp extends Help {

    public static final String forHelp = "Syntax: FOR [ variable start end increment ]\n" +
            "[ command(s) ]\nruns command(s) for each value specified in the range, i.e., from (start - end), going by increment\n" +
            "returns the value of the final command executed (or 0 if no commands are executed)\n" +
            "note, variable is assigned to each succeeding value so that it can be accessed by the command(s)";

    public forHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = forHelp;
    }

}