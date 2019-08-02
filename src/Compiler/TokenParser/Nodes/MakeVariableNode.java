/**
 * Assume that variables store the number returned by the argument given when evaluated. Meaning if
 * Make :a forward 50 is ran, a will be equal to 50.
 *
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.TokenParser.TokenType;
import Compiler.SyntaxParser;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import Compiler.Turtle;

public class  MakeVariableNode extends StructuredNode {
    public static final int VARIABLE_NAME_CHILD_INDEX = 0;
    public static final int EXPRESSION_CHILD_INDEX = 1;
    Map<String, List<Node>> variables;

    public MakeVariableNode(Token t, Map<String, List<Node>> savedVariables){
        super(t);
        variables = savedVariables;
    }

    @Override
    public double evaluate() {
        try{
            createVariableEntry();
            return 1;
        }
        catch(Exception e){
            throw new Error(e);
        }
    }

    private Map.Entry<String, List<Node>> createVariableEntry(){
        String key = getChildren().get(VARIABLE_NAME_CHILD_INDEX).getTokenValue();
        double value = getChildren().get(EXPRESSION_CHILD_INDEX).evaluate();
        Node valueNode = new ConstantNode(new Token(TokenType.Constant.toString(), value + ""));
        return Map.entry(key, List.of(valueNode));
    }

    public void validateSyntax(){
        if(!getChildren().get(VARIABLE_NAME_CHILD_INDEX).checkType(TokenType.Variable)){
            throw new Error(SyntaxParser.SYNTAX_ERROR);
        }
    }

    public Consumer<Turtle> executeNode(){
        if(evaluate() == 1){
            variables.put(createVariableEntry().getKey(), createVariableEntry().getValue());
        }
        else{
            throw new Error(SyntaxParser.SYNTAX_ERROR);
        }
        return turtle -> {};
    }
}
