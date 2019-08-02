package View.Help;

import View.UserHistory;

public class ifelseHelp extends Help {

    public static final String ifelseHelp = "Syntax: IFELSE expr \n" +
            "[ trueCommand(s) ] \n" +
            "[ falseCommand(s) ]\nif expr is not 0, runs the trueCommands given in the first list, otherwise runs the falseCommands given in the second list\n" +
            "returns the value of the final command executed (or 0 if no commands are executed)";

    public ifelseHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = ifelseHelp;
    }

}