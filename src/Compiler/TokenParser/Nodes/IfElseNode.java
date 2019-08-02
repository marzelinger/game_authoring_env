/**
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;
import Compiler.TokenParser.Token;
import Compiler.TokenParser.TokenType;
import Compiler.Turtle;
import Compiler.SyntaxParser;

import java.util.function.Consumer;

public class IfElseNode extends StructuredNode {
    public static final int EXPRESSION_INDEX =0;
    public static final int TRUE_COMMANDS_INDEX = 1;
    public static final int FALSE_COMMANDS_INDEX = 2;


    public IfElseNode(Token t){
        super(t);
    }

    @Override
    public double evaluate() {
        if(evaluateExpression()){
            return getChildren().get(TRUE_COMMANDS_INDEX).evaluate();
        }
        return getChildren().get(FALSE_COMMANDS_INDEX).evaluate();
    }

    public boolean evaluateExpression(){
        return getChildren().get(EXPRESSION_INDEX).evaluate() != 0;
    }

    @Override
    public void validateSyntax(){
        if(!getChildren().get(1).checkType(TokenType.ListStart) && getChildren().get(2).checkType(TokenType.ListStart)){
            throw new Error(SyntaxParser.SYNTAX_ERROR);
        }
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> {
            if(evaluateExpression()){
                ((ExecutableNode)getChildren().get(TRUE_COMMANDS_INDEX)).execute().accept(turtle);
            }
            else{
                ((ExecutableNode)getChildren().get(FALSE_COMMANDS_INDEX)).execute().accept(turtle);
            }
        };
        return action;
    }
}
