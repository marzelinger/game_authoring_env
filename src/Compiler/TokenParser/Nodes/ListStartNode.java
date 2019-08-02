/**
 * Make the assumption that a list will always return the evaluate method of the last element inside of it.
 * @author mpz5
 */

package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.TokenParser.TokenType;
import Compiler.Turtle;
import Compiler.SyntaxParser;

import java.util.function.Consumer;

public class ListStartNode extends StructuredNode{

    public ListStartNode(Token t){
        super(t);
    }

    public double evaluate() {
        if(getChildren().size() > 1){
            return getChildren().get(getChildren().size() - 2).evaluate();
        }
        return 0;
    }

    @Override
    public void validateSyntax() {
        if(!getChildren().get(getChildren().size()-1).checkType(TokenType.ListEnd)){
            throw new Error(SyntaxParser.SYNTAX_ERROR);
        }
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> {
            for(Node child : getChildren()){
                if(child.checkExecutable()){
                    ((ExecutableNode)child).execute().accept(turtle);
                }
            }
        };
        return action;
    }

}
