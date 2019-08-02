package View.Help;

import View.UserHistory;

/**
 * @author Ryan Piersma
 * Help subclass demonstrating how to use the towards command in SLogo
 */
public class towardsHelp extends Help {

    public static final String towardsHelp = "Syntax: TOWARDS x y \n turns turtle to face the point (x, y), where (0, 0) is the center of the screen\n" +
            "returns the number of degrees turtle turned\n";

    public towardsHelp(UserHistory userHistory)
    {
        super(userHistory);
        this.helpString = towardsHelp;
    }

}