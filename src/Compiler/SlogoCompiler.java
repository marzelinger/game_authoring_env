/**
 * This class implements the Compiler Interface and serves as the point of interaction between the model and the controller.
 * @author mpz5, Professor Duvall
 */

package Compiler;

import Compiler.TokenParser.*;
import Compiler.TokenParser.Nodes.Node;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SlogoCompiler implements Compiler {
    public static final String WHITESPACE = "\\s+";
    public static final String USER_DIR_PROPERTY = "user.dir";
    public static final String FILE_SEPARATOR_PROPERTY = "file.separator";
    public static final String COMPILER_FOLDER_NAME = "Compiler";
    public static final String SRC_FOLDER_NAME = "src";
    public static final String SLOGO_FILE_TYPE = ".slogo";
    public static final String SPACE = " ";
    public static final String MAKE = "make";
    public static final String TO = "to";

    TokenizerInterface tokenizer;
    SyntaxParserInterface syntaxParser;
    NodeFactoryInterface nf;
    String language;
    Map<String, List<Node>> savedFunctions;
    Map<String, List<Node>> savedVariables;
    ExecuterInterface executer;
    TurtleManager turtleManager;

    public SlogoCompiler(String nodeResource, String initialLanguageResource, Map<String, List<Node>> savedFunctions, Map<String, List<Node>> savedVariables, TurtleManager tm) {
        turtleManager = tm;
        this.savedFunctions = savedFunctions;
        this.savedVariables = savedVariables;
        tokenizer = new Tokenizer();
        nf = new NodeFactory(nodeResource, savedFunctions, savedVariables, tm);
        syntaxParser = new SyntaxParser(nf);
        language = initialLanguageResource;
        tokenizer.addResource(language);
        tokenizer.addSyntax();
    }

    @Override
    //API, UserSession, Executor
    /**
     * This function converts a string containing code to a list of lists of turtle statuses. It does this by using the SyntaxParser and Executor classes.
     *
     */
    public List<List<Object>> compile(String code, List<Turtle> activeTurtles, String codeLang) {
        List<List<Object>> turtleStatuses = new ArrayList();
        if(!codeLang.equals(language)){
            tokenizer.changeLanguage(codeLang);
            language = codeLang;
        }
        for(Turtle currTurtle : activeTurtles){
            executer = new Executer(currTurtle);
            String codeWithoutComments = tokenizer.removeComments(code);
            List<Token> tokens = parseText(codeWithoutComments.split(WHITESPACE));
            List<Node> treeHeads = syntaxParser.buildTrees(tokens, currTurtle);
            List<Object> statuses = executer.executeTrees(treeHeads);
            turtleStatuses.add(statuses);
        }
        return turtleStatuses;
    }

    /**
     * This function reads the a file into a string.
     * @param filename
     * @return
     */
    private String readFileToString(String filename) {
        final var END_OF_FILE = "\\z";
        var input = new Scanner(getClass().getResourceAsStream(filename));
        input.useDelimiter(END_OF_FILE);
        var result = input.next();
        input.close();
        return result;
    }

    /**
     * This function uses the tokenizer to convert a String array to a list of tokens.
     * @param text
     * @return
     */
    private List<Token> parseText(String[] text) {
        List<Token> tokens = new ArrayList();
        for (var s : text) {
            if (s.trim().length() > 0) {
                tokens.add(tokenizer.getSymbol(s));
            }
        }
        return tokens;
    }

    /**
     * Loads the given file
     * @param fileName
     * @return
     */
    public String loadFile(String fileName){
        String fileInput = readFileToString(fileName);
        return fileInput;
    }

    /**
     * This function saves a file with the commands to create all currently
     * @param fileName
     */
    public void save(String fileName){
        String command = "";
        String fileSeparator = System.getProperty(FILE_SEPARATOR_PROPERTY);
        command = command + createVariablesCommand() + " " + createFunctionsCommand();
        File file = new File( System.getProperty(USER_DIR_PROPERTY) + fileSeparator + SRC_FOLDER_NAME + fileSeparator + COMPILER_FOLDER_NAME + fileSeparator + fileName + SLOGO_FILE_TYPE);
        try{
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(command);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This function reverse engineers the command needed to create a user defined function that is currently saved.
     * @return
     */
    private String createFunctionsCommand(){
        String functionCommands = "";
        for(Map.Entry<String, List<Node>> e : savedFunctions.entrySet()){
            String functionCommand = TO + SPACE + e.getKey();
            List<Node> savedNodes = e.getValue();
            Node paramsHead = savedNodes.get(1);
            Node commandsHead = savedNodes.get(0);
            functionCommand = addToString(preOrderTraversal(paramsHead, new ArrayList<>()), functionCommand);
            functionCommand = addToString(preOrderTraversal(commandsHead, new ArrayList<>()), functionCommand);
            functionCommands = functionCommands + SPACE + functionCommand;
        }
        return functionCommands;
    }

    /**
     * This function adds a list of nodes to a string.
     * @param nodes
     * @param str
     * @return
     */
    private String addToString(List<Node> nodes, String str){
        for(Node n : nodes){
            str = str + SPACE + n.getTokenValue();
        }
        return str;
    }

    /**
     * Performs a pre order traversal of the tree rooted at the root node and returns a list with the tree nodes in the correct order.
     * @param root
     * @param traversal
     * @return
     */
    private List<Node> preOrderTraversal(Node root, List<Node> traversal){
        traversal.add(root);
        for(Node child : root.getChildren()){
            preOrderTraversal(child, traversal);
        }
        return traversal;
    }

    /**
     * reverse engineers the command to create a variable that is currently saved.
     * @return
     */
    private String createVariablesCommand(){
        String variableCommands = SPACE;
        for(Map.Entry<String, List<Node>> e : savedVariables.entrySet()){
            variableCommands = variableCommands + SPACE + MAKE + SPACE + e.getKey() + SPACE + e.getValue().get(0).getTokenValue();
        }
        return variableCommands;
    }
}

