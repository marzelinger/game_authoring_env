package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;

import java.util.function.Consumer;
import Compiler.Turtle;


public class SetPenColorNode extends ExecutableNode {

    Turtle turtle;

    public SetPenColorNode(Token t, Turtle turtle){
        super(t);
        this.turtle = turtle;
    }

    public double evaluate(){

        return getChildren().get(0).evaluate();
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> {
            turtle.setPenColor(getChildren().get(0).evaluate());
        };
        return action;
    }
}
