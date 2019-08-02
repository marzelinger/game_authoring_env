package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.TokenParser.TokenType;
import Compiler.Turtle;
import java.util.function.Consumer;

public class RepeatNode extends StructuredNode {
    public static final int EXPRESSION_INDEX = 0;
    public static final int COMMANDS_LIST_INDEX = 1;

    public RepeatNode(Token t){
        super(t);
    }

    public double evaluate() {
      return getChildren().get(COMMANDS_LIST_INDEX).evaluate();
    }


    public void validateSyntax(){
        if(!getChildren().get(1).checkType(TokenType.ListStart)){
            throw new Error("Invalid Syntax");
        }
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action;
            action = turtle -> {
                for (int i = 0; i < getChildren().get(EXPRESSION_INDEX).evaluate(); i++) {
                    for (Node child : getChildren().get(COMMANDS_LIST_INDEX).getChildren()) {
                        if (child.checkExecutable()) {
                            ((ExecutableNode) child).resetExecuted();
                            ((ExecutableNode) child).execute().accept(turtle);
                        }
                    }
                }
            };
        return action;
    }

}
