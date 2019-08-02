package Compiler.TokenParser.Nodes;

import Compiler.TokenParser.Token;
import Compiler.Turtle;

import java.util.function.Consumer;

public class SetBackgroundNode extends ExecutableNode{
    Turtle turtle;

    public SetBackgroundNode(Token t, Turtle turtle){
        super(t);
        this.turtle = turtle;
    }

    public double evaluate(){

        return getChildren().get(0).evaluate();
    }

    public Consumer<Turtle> executeNode(){
        Consumer<Turtle> action = turtle -> {
            turtle.setBackground(getChildren().get(0).evaluate());
        };
        return action;
    }



}
