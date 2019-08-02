package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.TokenParser.TokenType;
import Compiler.TurtleManager;
import Compiler.Turtle;
import Compiler.TurtleStatus;

import java.util.*;
import java.util.function.Consumer;

public class AskWithNode extends StructuredNode {

    //validateCondition method

    TurtleManager tsManager;
    public static final int CONDITIONS_LIST_INDEX = 0;
    public static final int COMMANDS_LIST_INDEX = 1;


    public AskWithNode(Token t, TurtleManager tsm) {
        super(t);
        this.tsManager = tsm;

    }

    public double evaluate() {
        //returns the value of last turtle that meets conditions

            int lastChildNode = getChildren().get(0).getChildren().size() - 1;
            return getChildren().get(lastChildNode).evaluate();

    }

    public void validateSyntax() {
        if (!getChildren().get(CONDITIONS_LIST_INDEX).checkType(TokenType.ListStart) ||
                !getChildren().get(COMMANDS_LIST_INDEX).checkType(TokenType.ListStart) ||
                (!(getChildren().get(CONDITIONS_LIST_INDEX).evaluate() == 0) &&
                !(getChildren().get(CONDITIONS_LIST_INDEX).evaluate() == 1))

        ) {
            throw new Error("Invalid Syntax");
        }

    }

    public Consumer<Turtle> executeNode() {

        double check = getChildren().get(CONDITIONS_LIST_INDEX).evaluate();
        if(getChildren().get(CONDITIONS_LIST_INDEX).evaluate() == 1){ //if the conditions match
            getChildren().get(COMMANDS_LIST_INDEX).evaluate();
        }
        else{
            for(int i = 0; i <getChildren().get(COMMANDS_LIST_INDEX).getChildren().size(); i++ ){
                if( getChildren().get(COMMANDS_LIST_INDEX).getChildren().get(i).checkType(TokenType.ListEnd)){
                    break;
                }
                ((ExecutableNode) getChildren().get(COMMANDS_LIST_INDEX).getChildren().get(i)).setExecuted();
            }

        }

        return turtle -> {};


    }


}



