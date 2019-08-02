package View.Help;

import View.UserHistory;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/** @author Ryan Piersma
 *  @date 11.2.18
 *  Purpose: This class implements an abstract structure for any help message that will pop up in the command
 *  history box for SLogo
 *  Assumptions: There is a UserHistory class that represents the command history box, subclasses will be named
 *  in the format xHelp, where x is the name of the command that will allow for it to be accessed from the main view
 *  Dependencies: JavaFX Font, JavaFX Text
 *  Example: Help a = new Initializer(helpMessage, commandHistory);
 *
 */
public abstract class Help {

    protected static final String LOAD_STRING = "";
    protected String helpString;
    protected UserHistory historyAccess;

    /**
     * Constructor for a Help object
     * @param helpMessage Help message that will be displayed when the object is called on
     * @param commandHistory Command history box that the Help object is associated with
     */
    public Help(String helpMessage, UserHistory commandHistory)
    {
        this.helpString = helpMessage;
        this.historyAccess = commandHistory;
    }

    /**
     * Constructor for a help object when a help message is not immediately given
     * @param commandHistory Command history box that the Help object is associated with
     */
    public Help(UserHistory commandHistory)
    {
        this.helpString = LOAD_STRING;
        createHelpText();
        this.historyAccess = commandHistory;
    }

    /**
     * Method that loads the Help object's help text string into the user command history after
     * wrapping it in a JavaFX text object
     */
    public void loadHelpMessage()
    {
        Text helpText;
        helpText = createHelpText();
        historyAccess.updateUserHistory(helpText);
    }

    private Text createHelpText(){
        Text helpText = new Text();
        helpText.setFont(Font.font("Monospaced", 14));
        helpText.setText(helpString);
        return helpText;
    }
}
