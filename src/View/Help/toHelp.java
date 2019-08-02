package View.Help;

import View.UserHistory;

public class toHelp extends Help {

    public static final String toHelp = "Syntax: TO commandName\n" +
            "[ variable(s) ]\n" +
            "[ command(s) ]\nassigns command(s) given in the second list to commandName using parameters given in first list as variables\n" +
    "when commandName is used later in a program, any given values are assigned to variables that can be accessed when the command list is run \n and the value of the final command executed is returned (or 0 if no commands are executed)\n" +
    "returns 1 if command is successfully defined, otherwise 0";

    public toHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = toHelp;
    }

}