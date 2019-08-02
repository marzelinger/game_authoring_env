/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.TokenParser.TokenType;
import Compiler.Turtle;
import Compiler.SyntaxParser;

import java.util.function.Consumer;

public class IfNode extends StructuredNode {
    public static final int EXPRESSION_INDEX = 0;
    public static final int COMMANDS_INDEX = 1;


    public IfNode(Token t){
        super(t);
    }

    @Override
    public double evaluate() {
        if(evaluateExpression()){
            return getChildren().get(COMMANDS_INDEX).evaluate();
        }
        return 0;
    }

    private boolean evaluateExpression(){
        return getChildren().get(EXPRESSION_INDEX).evaluate() != 0;
    }

    @Override
    public void validateSyntax(){
        if(!getChildren().get(1).checkType(TokenType.ListStart)){
            throw new Error(SyntaxParser.SYNTAX_ERROR);
        }
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action;
        if(evaluateExpression()){
            action = turtle -> {
                for(Node child : getChildren().get(COMMANDS_INDEX).getChildren()){
                    if(child.checkExecutable()){
                        ((ExecutableNode)child).execute().accept(turtle);
                    }
                }
            };
        }
        else{
            action = turtle -> {};
        }
        return action;
    }


}
