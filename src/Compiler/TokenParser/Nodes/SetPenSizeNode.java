package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;

import java.util.function.Consumer;
import Compiler.Turtle;

public class SetPenSizeNode extends ExecutableNode {

    Turtle turtle;

    public SetPenSizeNode(Token t, Turtle turtle){
        super(t);
        this.turtle = turtle;
    }

    public double evaluate(){

        return getChildren().get(0).evaluate();
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> {
            turtle.setPenSize(getChildren().get(0).evaluate());
        };
        return action;
    }
}
