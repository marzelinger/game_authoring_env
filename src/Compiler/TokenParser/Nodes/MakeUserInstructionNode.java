/**
 * This node is used to save user defined instructions to the map of saved functions. Command Nodes then access this map to define themselves.
 * author @mpz5
 */


package Compiler.TokenParser.Nodes;

import Compiler.SyntaxParser;
import Compiler.TokenParser.Token;
import Compiler.TokenParser.TokenType;
import Compiler.Turtle;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MakeUserInstructionNode extends StructuredNode {
    public static final int COMMAND_NAME_CHILD_INDEX = 0;
    public static final int PARAM_LIST_CHILD_INDEX = 1;
    public static final int COMMAND_LIST_CHILD_INDEX = 2;

    Map<String, List<Node>> functions;

    public MakeUserInstructionNode(Token t, Map<String, List<Node>> savedFunctions){
        super(t);
        functions = savedFunctions;
    }

    @Override
    public double evaluate() {
        try{
            createCommandEntry();
            return 1;
        } catch (Exception e){
            throw new Error(e);
        }
    }

    private Map.Entry<String, List<Node>> createCommandEntry(){
        String key = getChildren().get(COMMAND_NAME_CHILD_INDEX).getTokenValue();
        Node commandList = getChildren().get(COMMAND_LIST_CHILD_INDEX);
        Node paramList = getChildren().get(PARAM_LIST_CHILD_INDEX);
        return Map.entry(key, List.of(commandList, paramList));
    }

    @Override
    public void validateSyntax() {
        if(!(getChildren().get(COMMAND_NAME_CHILD_INDEX).checkType(TokenType.Command) && getChildren().get(PARAM_LIST_CHILD_INDEX).checkType(TokenType.ListStart) && getChildren().get(COMMAND_LIST_CHILD_INDEX).checkType(TokenType.ListStart))){
            throw new Error(SyntaxParser.SYNTAX_ERROR);
        }
        List<Node> paramList = getChildren().get(PARAM_LIST_CHILD_INDEX).getChildren();
        for(int i = 0; i < paramList .size(); i ++){
            if(i == paramList.size() - 1){
                if(!paramList.get(i).checkType(TokenType.ListEnd)){
                    throw new Error(SyntaxParser.SYNTAX_ERROR);
                }
            }
            else if(!paramList.get(i).checkType(TokenType.Variable)){
                throw new Error(SyntaxParser.SYNTAX_ERROR);
            }
        }
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> {};
        functions.put(createCommandEntry().getKey(), createCommandEntry().getValue());
        return action;
    }
}
