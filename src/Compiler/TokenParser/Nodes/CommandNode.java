/**
 * This class is the Node class for a User Defined Command Node. It uses the saved functions to access the necessary params and the tree of commands that must be executed. This class extends the ExecutableNode class, which extends the Node class.
 *
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;
import Compiler.TokenParser.Token;
import java.util.Map;
import Compiler.TokenParser.TokenType;
import Compiler.Turtle;
import java.util.*;
import java.util.function.Consumer;
import Compiler.SyntaxParser;

public class CommandNode extends ExecutableNode{
    private boolean isSet;
    private Node commandListRoot;
    private Node variableListRoot;
    private int numArgs;
    Map<String, List<Node>> functions;
    String name;

    /**
     * saved functions will be a map that maps the string name of the command to a list of nodes:
     *     node one will be the root node for the command list.
     *     node two will be the root node for the variable list.
     */
    public CommandNode(Token t, Map<String, List<Node>> savedFunctions){
        super(t);
        functions = savedFunctions;
        name = t.getValue();
        trySetNode();
    }

    /**
     * Returns the value that this node evaluates to. Used when this node is an argument for another node and that parent node needs access to the return value.
     * @return
     */
    public double evaluate(){
        if(!isSet){
            trySetNode();
        }
        if (isSet) {
            return commandListRoot.evaluate();
        }
        throw new Error("Invalid node if not inside of a TO statement");
    }

    /**
     * Tries to set the commandListRoot, variableListRoot, and numArgs instance variable values depending on whether or not this Command has been defined by the user yet.
     */
    public void trySetNode(){
        if(!isSet){
            if (functions.containsKey(name)) {
                isSet = true;
                commandListRoot = functions.get(name).get(0);
                variableListRoot = functions.get(name).get(1);
                numArgs = variableListRoot.getChildren().size();
            } else {
                isSet = false;
            }
        }
    }

    /**
     * This function will iterate through the original list of commands that this function must execute and replace all variable nodes with the correct param value
     */
    private void insertParams(){
        //iterate through all of the nodes, for any nodes that are variables assign the correct value
        List<Node> visitedNodes = new ArrayList<>();
        visitedNodes.add(commandListRoot);
        Map<String, Double> paramsMap = createParamsVariableMap();
        while(!visitedNodes.isEmpty()){
            Node currentNode = visitedNodes.get(0);
            if(currentNode.checkExecutable()){
                ((ExecutableNode)currentNode).resetExecuted();
            }
            for(Node child : currentNode.getChildren()){
                visitedNodes.add(child);
            }
            if(currentNode.checkType(TokenType.Variable)){
                if(paramsMap.containsKey(currentNode.getTokenValue())){
                    ((VariableNode)currentNode).assignValue(paramsMap.get(currentNode.getTokenValue()));
                }
            }
            visitedNodes.remove(0);
        }
    }

    /**
     * Returns the number of arguments defined by the user for this node
     * @return
     */
    public int getCommandNumArgs(){
        if(isSet){
            return createParamsList().size();
        }
        return 0;
    }

    /**
     * Will convert the tree of parameters into a list holding the variable string. This will associate the string identifier of the variable
     * @return
     */
    private List<String> createParamsList(){
        List<String> paramsList = new ArrayList<>();
        for(Node param : variableListRoot.getChildren()){
            if(!param.checkType(TokenType.ListEnd)){
                paramsList.add(param.getTokenValue());
            }
        }
        return paramsList;
    }

    /**
     * Creates a map of the variable value for the parameter defined by the user and the value allocated to it when the function is called.
     * @return
     */
    private Map<String, Double> createParamsVariableMap(){
        List<String> paramsList = createParamsList();
        Map<String, Double> paramsVariableMap = new HashMap();
        if(getChildren().size() != paramsList.size()){
            throw new Error(SyntaxParser.SYNTAX_ERROR);
        }
        for(int i = 0; i < getChildren().size(); i++){
            Node child = getChildren().get(i);
            paramsVariableMap.put(paramsList.get(i), child.evaluate());
        }

        return paramsVariableMap;
    }

    /**
     * Implementation of the abstract executeNode function from the ExecutableNode class. Returns a Consumer for a turtle that executes the user defined function on that turtle.
     * @return
     */
    @Override
    public Consumer<Turtle> executeNode() {
        trySetNode();
        if(!isSet){
            throw new Error(SyntaxParser.SYNTAX_ERROR);
        }
        insertParams();
        Consumer<Turtle> action = turtle -> {
            for(Node command : commandListRoot.getChildren()){
                if(command.checkExecutable()){
                    ((ExecutableNode)command).execute().accept(turtle);
                }
            }
        };
        return action;
    }
}
