package View;

import View.Help.Help;
import View.Help.GeneralHelp;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/*
    Reason for inclusion in code masterpiece: see Help class for complete reasoning. This class demonstrates the
    factory design pattern by allowing for the creation of different Help objects through a generic createHelp method
    with reflection.
 */
/** @author Ryan Piersma
 *  @date 11.2.18
 *  Purpose: This class implements initialization of the correct Help object type reflectively,
 *  using a factory design pattern to produce new help objects as needed during the execution of a run of
 *  SLogo
 *  Assumptions: There is a Help abstract class whose subclasses are named according to the pattern xHelp,
 *  where the x is the name of a command that "help" would be called on from the GUI command window
 *  Dependencies: Exception, Help, GeneralHelp, Constructor
 *  Example: There is only a default constructor, so HelpFactory a = new HelpFactory();
 *
 */
public class HelpFactory {

    public static final int HELP_LENGTH = 4;
    public static final String HELP_STRING = "help";
    public static final String HELP_INTIIALIZE_ERROR = "Error in initializing help class: ";

    /**
     * This method creates the correct Help instance (with the right help message) reflectively
     * @param helpType String extracted from the command history used to reflectively generate a Help instance
     * @param userHistory the Command history of the current SLogo instance
     * @return the correct Help object to operate on from UserSession
     */
    public Help createHelp(String helpType, UserHistory userHistory)
    {
        Object help = null;
        try {
            Class<?> helper = Class.forName("View.Help." + helpType + "Help");
            Constructor<?> helpConstruct = helper.getConstructor(UserHistory.class);
            help = helpConstruct.newInstance(userHistory);
        }
        catch (ClassNotFoundException c)
        {
            System.err.println("Error in initializing help class: class not found exception");
            GeneralHelp helper = new GeneralHelp(userHistory);
            helper.loopThruCommands();
            return helper;
        }
        catch (Exception e)
        {
            exceptionCatch(e);
        }

        return (Help)help;
    }

    /**
     * Use HelpFactory to check if a command starts with "help"
     * @param command the command that was typed into the command window
     * @return a boolean representing whether the command was a help command or not
     */
    public boolean helpCheck(String command)
    {
        String removeWhitespace = command.trim();
        if (command.length() >= HELP_LENGTH) {
            String substringCheck = removeWhitespace.toLowerCase().substring(0, HELP_LENGTH );
            if (substringCheck.equals(HELP_STRING))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public void exceptionCatch(Exception e)
    {
        System.err.println(HELP_INTIIALIZE_ERROR + e.getClass());
    }

}
