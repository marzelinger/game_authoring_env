/**
 * @author Ethan Ho
 * @author Ryan Piersma
 * @author Marley Zelinger
 * @author Janice Liu (?)
 *
 */

/**
 * This class serves as the controller for the view and model of SLogo. It communicates with front end to receive user interactions with the GUI and communicates these interactions to the back end where all parsing and code creation occurs. The result of this compiling, a list of lists of turtle statuses (one list per turtle is returned to the controller, which then sends these results to the front end for updating.
 */
package UserSession;

import Compiler.TokenParser.Nodes.Node;
import Compiler.TurtleStatus;
import Compiler.SlogoCompiler;
import Compiler.Compiler;
import Compiler.*;
import View.*;
import View.Help.Help;
import View.Help.GeneralHelp;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.*;

public class UserSession implements UserSessionAPI{
    public static final String DEFAULT_INITIAL_LANGUAGE_FILE = "languages.English";
    public static final String COMMAND_ERROR = "The command you have entered is not correct. Try 'help' to learn more.";
    public static final String HELP_STRING = "help";
    public static final String LANGUAGES_FOLDER = "languages.";
    public static final String SAVE_FILE = "save.slogo";
    public static final String SAVE = "save";
    public static final String LOAD = "load";
    private Compiler compiler;
    private MainView view;
    private SetupView setup;
    private UpdateView updater;
    private List<List<Object>> currStatusLists;
    private List<Double> commandResults;
    private String instruction;
    private HelpFactory helpChecker = new HelpFactory();
    private TurtleManager tm;
    private Map<String, List<Node>> savedFunctions = new HashMap<>();
    private Map<String, List<Node>> savedVariables = new HashMap<>();
    private int statusCounter = 0;

    //LOAD BOOLEAN ACCESS: view.getUserScreen().getSaveState()
    //SAVE BOOLEAN ACCESS: view.getUserScreen().getLoadState()

    public UserSession(MainView view, String nodesResource) {
        tm = new TurtleManager();
        tm.createTurtle(1);
        compiler = new SlogoCompiler(nodesResource, DEFAULT_INITIAL_LANGUAGE_FILE, savedFunctions, savedVariables, tm);
        this.view = view;
        this.setup = new SetupView(view);
        this.view.setSetupView(this.setup);
        this.updater = new UpdateView(view, setup);
        view.setSetupView(setup);

    }

    /**
     * This function takes in a string of code and asks the back end to compile that code. It then returns the response from the back end or displays a Syntax Error.
     * @param code
     * @return
     */
    public List<List<Object>> runCode(String code){
        List<List<Object>> statuses = null;
        try {
            statuses = compiler.compile(code, tm.getActiveTurtles(), LANGUAGES_FOLDER + view.getUserScreen().getLanguage());
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            System.err.println("Command incorrect");
            createText("Syntax Error");
            view.setInputState(false);
        }
        catch (Error e)
        {
            e.printStackTrace();
            System.err.println("Command incorrect");
            createText("Syntax Error");
            view.setInputState(false);
        }
        return statuses;
    }

    public void updateSession(){
        boolean helpUpdate = false;
        if(view.getInputState()){
            instruction = view.getOutput();
            if(instruction.equals(SAVE)){
                compiler.save(instruction);
                view.setInputState(false);
                return;
            }
            else if(instruction.equals(LOAD)){
                String command = compiler.loadFile(SAVE_FILE);
                runCode(command);
                view.setInputState(false);
                return;
            }
            helpUpdate = helpChecker.helpCheck(instruction);
            Help getHelp = null;
            if (helpUpdate)

            {
                if (instruction.equals(HELP_STRING))
                {
                    GeneralHelp getHelper = new GeneralHelp(view.getUserHistory());
                    getHelper.loopThruCommands();
                }
                else {
                    getHelp = helpChecker.createHelp(instruction.substring(HELP_STRING.length() + 1).trim().toLowerCase(), view.getUserHistory());
                    getHelp.loadHelpMessage();
                }
            }

            currStatusLists = runCode(instruction);

            for(List<Object> currStatus : currStatusLists){
                try {
                    List<TurtleStatus> tsList = (List<TurtleStatus>) currStatus.get(0);
                    int getStatus = (currStatus.size() <= 1) ? 0 : tsList.size() - 1;
                    double currID = tsList.get(getStatus).getID();
                    boolean currState = tsList.get(getStatus).getActive();
//                    System.out.print("current turtle ID is: " + currID + "\n");
                    if(!view.getUserScreen().getTurtleViewMap().containsKey(currID)){
//                        System.out.print("Current turtle has been added\n");
                        view.getUserScreen().addTurtle(currID);
                    }
                    for (int i = statusCounter; i <= getStatus; i++)
                    {
                    updater.updateLoop((TurtleView) view.getUserScreen().getTurtleViewMap().get(currID), tsList.get(i), currState, view.getUserScreen().getWidth() / 2, view.getSetupView().getMainView().getUserScreen().getHeight() / 2, savedFunctions, savedVariables);
                    commandResults = (ArrayList<Double>) currStatus.get(1);
                    int getCommandReturn = (commandResults.size() <= 1) ? 0 : commandResults.size() - 1;
                    if (commandResults.size() != 0) {
                        createText(Double.toString(commandResults.get(getCommandReturn)));
                    }
                     statusCounter++;
                    }
                } catch (NullPointerException npe) {
                    if (!helpUpdate){
                        createText(COMMAND_ERROR);}
                } catch (NumberFormatException ne) {
                    if (!helpUpdate){
                        createText(COMMAND_ERROR);}
                } catch (Error e) {
                    if (!helpUpdate){
                        createText(COMMAND_ERROR);}
                }
            }
            view.setInputState(false);
        }
        /**
         * getSaveState determines if the the save button has been clicked
         * view.getSaveState()
         */
        else if(true){
            String fileName = view.getOutput();
            Properties properties = new Properties();
        }
    }

    private void createText(String message){
        Text text = new Text();
        text.setFont(Font.font("Monospaced", 14));
        text.setText(message);
        view.getUserHistory().updateUserHistory(text);
    }

}
