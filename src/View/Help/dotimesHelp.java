package View.Help;

import View.UserHistory;

public class dotimesHelp extends Help {

    public static final String dotimesHelp = "Syntax: DOTIMES [\n variable limit] \n [ command(s) ] \nruns command(s) for each value specified in the range, i.e., from (1 - limit) inclusive \n" +
            "returns the value of the final command executed (or 0 if no commands are executed)\n" +
            "note, variable is assigned to each succeeding value so that it can be accessed by the command(s)";

    public dotimesHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = dotimesHelp;
    }

}