package View.Help;

import View.UserHistory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/** @author Ryan Piersma
 *  @date 11.2.18
 *  Purpose: Special instance of help class that is initialized when the String "help" is typed alone or followed by
 *  a string that is not recognized as a SLogo command
 *  Assumptions: All SLogo commands are correctly represented by Help classes such that xHelp is the help class for the
 *  SLogo command x
 *  Dependencies: BufferedReader, FileReader, IOException
 *  Example: GeneralHelp a = new GeneralHelp(commandHistory);
 *
 */
public class GeneralHelp extends Help{

    public static final String commandListFilepath = "data\\commandlist.txt"; //file with list of commands in SLogo
    public static final String generalHelpMessage = "This is a list of the commands that can be used in SLogo. \n"
            + "Type help plus one of these commands to learn about a specific command";
    public static final String commandListNotFound = "The commandlist file could not be found. Check the filepath in the" +
            "GeneralHelp class";
    /**
     * GeneralHelp constructor
     * @param commandHistory Command history for instance of SLogo
     */
    public GeneralHelp(UserHistory commandHistory)
    {
        super(commandHistory);
    }

    /**
     * Method that reads a list of commands from the filepath given in the variable commandListFilepath and prints
     * them as the list of commands that can be used for this version of SLogo
     */
    public void loopThruCommands()
    {
        this.helpString = generalHelpMessage;
        loadHelpMessage();
        try {
            BufferedReader readCommandList = new BufferedReader(new FileReader(commandListFilepath));
            String currLine;
            while ((currLine = readCommandList.readLine()) != null)
            {
                this.helpString = currLine;
                loadHelpMessage();
            }
        }
        catch (IOException io)
        {
            System.err.println(commandListNotFound);
        }
    }

}
