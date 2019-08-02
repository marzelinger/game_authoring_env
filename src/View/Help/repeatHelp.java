package View.Help;

import View.UserHistory;

public class repeatHelp extends Help {

    public static final String repeatHelp = "Syntax: REPEAT expr \n [ command(s) ] \nruns command(s) given in the list the value of expr number of times\n" +
            "returns the value of the final command executed (or 0 if no commands are executed)\n" +
            "note, the value of the current iteration, starting at 1, is automatically assigned to the variable :repcount so that it can be accessed by the command(s)";

    public repeatHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = repeatHelp;
    }

}